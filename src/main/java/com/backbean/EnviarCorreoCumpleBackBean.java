package com.backbean;

import com.entidades.Contacto;
import com.servicios.service.ContactoService;
import com.utils.SystemSecurityException;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

/**
 * Created by Pablo Sevilla on 13/2/2017.
 */
@Named
@Scope("view")
public class EnviarCorreoCumpleBackBean  implements Serializable {

    @Autowired
    ContactoService contactoService;

    private Session session;
    private String msgError;
    private String txtPara;
    private String txtAsunto;
    private String txtContenido;
    private final Properties properties = new Properties();
    private Integer iDia;
    private Integer iMes;
    private List<Contacto> listaContactos;

    private UploadedFile file;




    /*
    Metodos
     */
    @PostConstruct
    public void init() throws SystemSecurityException {
        Calendar cal = Calendar.getInstance();
        iDia = cal.get(Calendar.DAY_OF_MONTH);
        iMes = cal.get(Calendar.MONTH) + 1;
        obtenerCorreos(iMes, iDia);
    }

    public  void obtenerCorreos(Integer mes, Integer dia) {
        try {
            listaContactos = contactoService.obtenerContactoCumple(mes, dia);
        } catch (Exception e) {
            setMsgError("Error: " + e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "obtenerGrupos", getMsgError()));
            return;
        }


        if (listaContactos.size() > 0) {
            txtPara = "";
            txtContenido = "";
            for (Integer cont = 0; cont < listaContactos.size(); cont++) {
                if (listaContactos.get(cont).getCorreo() != null) {
                    txtPara += listaContactos.get(cont).getCorreo() + ", ";
                }
            }
        }

    }

    public void sendMail() {
        setSession(Session.getDefaultInstance(getProperties()));
        getProperties().put("mail.smtp.host", "smtp.gmail.com");
        getProperties().put("mail.smtp.starttls.enable", "true");
        getProperties().put("mail.smtp.port",465);
        getProperties().put("mail.smtp.scoketFactory.class", "javax.net.ssl.SSLSocketFactory");
        getProperties().put("mail.smtp.mail.sender","pasj08@gmail.com");
        getProperties().put("mail.smtp.user", "pasj08@gmail.com");
        getProperties().put("mail.smtp.auth", "true");

        try{
            MimeMessage message = new MimeMessage(getSession());
            message.setFrom(new InternetAddress((String) getProperties().get("mail.smtp.mail.sender")));
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(getTxtPara()));
            message.setSubject("Prueba");
            message.setText(txtContenido);
            Transport t = getSession().getTransport("smtp");
            t.connect((String) getProperties().get("mail.smtp.user"), "sevillajarquin");
            t.sendMessage(message, message.getAllRecipients());
            t.close();
        }catch (MessagingException me){
            setMsgError("Error: " + me.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "obtenerGrupos", getMsgError()));
            return;
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void upload() {
        String path = FacesContext.getCurrentInstance().getExternalContext()
                .getRealPath("/");

    }


    /*
    public void upload() {
        String path = FacesContext.getCurrentInstance().getExternalContext()
                .getRealPath("/");

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

        String fileName = FilenameUtils.getName(file.getFileName());
        String fileNamePrefix = FilenameUtils.getBaseName(fileName) + "_";
        String fileNameSuffix = "." + FilenameUtils.getExtension(fileName);

        File uploadFolder = new File("C:\\Proyectos\\contactos\\upload");


        try {
            File result = File.createTempFile(fileNamePrefix, fileNameSuffix, uploadFolder);

            FileOutputStream fileOutputStream = new FileOutputStream(result);
            byte[] buffer = new byte[1024];
            int bulk;

            InputStream inputStream = file.getInputstream();
            while (true) {
                bulk = inputStream.read(buffer);
                if (bulk < 0) {
                    break;
                }

                fileOutputStream.write(buffer, 0, bulk);
                fileOutputStream.flush();
            }

            String value = FacesContext.getCurrentInstance().
                    getExternalContext().getRequestParameterMap().get("editmail");
            setTxtContenido( result.getName() );

            RequestContext.getCurrentInstance().update("editmail");
            fileOutputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            FacesMessage error = new FacesMessage("The files were not uploaded!");
            FacesContext.getCurrentInstance().addMessage(null, error);
        }


    } //end of handleFileUpload()



public void uploadListener(FileUploadEvent event) {
        file = event.getFile();
        txtContenido = file.getFileName();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

        String fileName = FilenameUtils.getName(event.getFile().getFileName());
        String fileNamePrefix = FilenameUtils.getBaseName(fileName) + "_";
        String fileNameSuffix = "." + FilenameUtils.getExtension(fileName);

        File uploadFolder = new File("/var/webapp/uploads");


        try {
            File result = File.createTempFile(fileNamePrefix, fileNameSuffix, uploadFolder);

            FileOutputStream fileOutputStream = new FileOutputStream(result);
            byte[] buffer = new byte[1024];
            int bulk;

            InputStream inputStream = event.getFile().getInputstream();
            while (true) {
                bulk = inputStream.read(buffer);
                if (bulk < 0) {
                    break;
                }

                fileOutputStream.write(buffer, 0, bulk);
                fileOutputStream.flush();
            }

            fileOutputStream.close();
            inputStream.close();


            String value = FacesContext.getCurrentInstance().
                    getExternalContext().getRequestParameterMap().get("editor_input");
            setTxtContenido(value +  result.getName()+"\" />");

            RequestContext.getCurrentInstance().update("editor_input");

            FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } catch (IOException e) {
            e.printStackTrace();
            FacesMessage error = new FacesMessage("The files were not uploaded!");
            FacesContext.getCurrentInstance().addMessage(null, error);
        }
    }
     */

    /*
    Propiedades
     */

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getMsgError() {
        return msgError;
    }

    public void setMsgError(String msgError) {
        this.msgError = msgError;
    }

    public String getTxtPara() {
        return txtPara;
    }

    public void setTxtPara(String txtPara) {
        this.txtPara = txtPara;
    }

    public String getTxtAsunto() {
        return txtAsunto;
    }

    public void setTxtAsunto(String txtAsunto) {
        this.txtAsunto = txtAsunto;
    }

    public String getTxtContenido() {
        return txtContenido;
    }

    public void setTxtContenido(String txtContenido) {
        this.txtContenido = txtContenido;
    }

    public Properties getProperties() {
        return properties;
    }

    public List<Contacto> getListaContactos() {
        return listaContactos;
    }

    public void setListaContactos(List<Contacto> listaContactos) {
        this.listaContactos = listaContactos;
    }

    public Integer getiDia() {
        return iDia;
    }

    public void setiDia(Integer iDia) {
        this.iDia = iDia;
    }

    public Integer getiMes() {
        return iMes;
    }

    public void setiMes(Integer iMes) {
        this.iMes = iMes;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
}
