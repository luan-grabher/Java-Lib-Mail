package test;

import main.Gmail;

public class test {
    public static void main(String[] args){
        Gmail gmail = new Gmail("miau@gmail.com", "cat");
        
        gmail.enviaZAC("mial@maiu.com.br","assunto", "test message");
    }
}
