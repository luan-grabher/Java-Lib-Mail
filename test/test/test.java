package test;

import main.Gmail;

public class test {
    public static void main(String[] args){
        Gmail gmail = new Gmail("miau@gmail.com", "cat");
        
        gmail.enviaZac("mial@maiu.com.br","assunto", "test message");
    }
}
