
package test;

import main.Gmail;

public class teste {
    public static void main(String[] args) {
    	Gmail mail = new Gmail("xxxxx@gmail.com","xxxxx");
        
        if (mail.enviaZAC("xxx@xxxxx,xx@xx.com", "Isso é um teste", "Essa mensagem é apenas um teste.<br>Aqui eu quebrei a linha.")){
            System.out.println("Enviado!");
        }else {
            System.out.println("Não enviado!");
        }
    }
}