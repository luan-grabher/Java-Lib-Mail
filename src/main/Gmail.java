
package main;

import java.io.File;
import java.util.Date;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class Gmail {
    private final String login;
    private final String senha;
    
    public Gmail(String loginn, String senhaa){
        login = loginn;
        senha = senhaa;
    }
    public boolean envia(String para, String assunto, String mensagem, String lugarAssinatura){
        HtmlEmail email = new HtmlEmail();
        String[] lista_para = para.split(",");

	try {
            //ENVIA  EMAIL
            
            email.setCharset("UTF-8");
            email.setDebug(true);
            email.setHostName("smtp.gmail.com");
            email.setAuthentication(login,senha);
            email.setSSL(true);
            for (String para_l : lista_para) {
                email.addTo(para_l);
            }
            email.setFrom(login); //aqui necessita ser o email que voce fara a autenticacao
            email.setSubject(assunto);
            
            //Assinatura
            String assinatura = "";
            if(!"".equals(lugarAssinatura)){
                String cid = email.embed(new File(lugarAssinatura));
                if (!"".equals(cid)){
                    assinatura = "<img src='cid:" + cid + "' height=\"auto\" width=\"800\"></img>";
                }
            }
            
            
            email.setMsg(mensagem + assinatura);
            email.send();
            return true;
	} catch (EmailException e) {
            System.out.println(e);
            return false;
	} 
    }
    public boolean envia(String para, String assunto, String mensagem){
        return envia(para, assunto, mensagem,"");
    }
    public boolean enviaZAC(String para, String assunto, String mensagem){
        try{
            //DEFINE BOM DIA /BOA TARDE /BOA NOITE

            String msg;
            String saudacao;
            int hora = getHour();

            if (hora < 13) {
                saudacao = "Bom dia!<br><br>";
            }else if (hora < 19 ) {
                saudacao = "Boa Tarde!<br><br>";
            }else {
                saudacao = "Boa noite!<br><br>";
            }

            //DEFINE ASSINATURA
            String assinatura;
            String lugarAssinatura = "\\\\HEIMERDINGER\\docs\\Informatica\\Assinaturas de E-mail\\ZAC.png";
            assinatura = "<br><br>Atenciosamente,<br><br>";

            //DEFINE MENSAGEM FINAL
            msg = saudacao + mensagem + assinatura; 

            return envia(para, assunto, msg, lugarAssinatura);
        }catch(Exception e){
            System.out.println("Erro ao enviar email formato ZAC: " + e);
            e.printStackTrace();
            return false;
        }
             
    }
    public int getHour() {
        try{
            Date date = new Date();
            return date.getHours();
        }catch(Exception e){
            System.out.println("Erro ao pegar hora: " + e);
            e.printStackTrace();
            return 0;
        }
    }
    
}
