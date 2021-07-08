package com.example.springdemo.controller;

import org.junit.Assert;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class test {

    public static void main(String[] args) {

//        String src = "\"列转行\"的数值列必须是同一类型，请检查输入表与当前表中的\"列转行\"设置";
        //             "\"列转行\"的数值列必须是同一类型，请检查输入表与当前表中的\"列转行\"设置"
//        String src = "Les colonnes numériques de \"Colonne à Ligne\" doivent être du même type, veuillez vérifier le paramètre \"Colonne à Ligne\" dans le tableau d'entrée et le tableau actuel";
//                     "Les colonnes numériques de \"Colonne à Ligne\" doivent être du même type, veuillez vérifier le paramètre \"Colonne à Ligne\" dans le tableau d'entrée et le tableau actuel"
//        String src = "Числовые столбцы в \"Столбец в строку \" должны быть одного типа, пожалуйста, проверьте настройку \"Столбец в строку \" во входной таблице и в текущей таблице.";
        //             "Числовые столбцы в \"Столбец в строку \" должны быть одного типа, пожалуйста, проверьте настройку \"Столбец в строку \" во входной таблице и в текущей таблице."
        String src = "As colunas numéricas de \" Coluna para Linha \" devem ser do mesmo tipo, verifique a configura??o \"Coluna para Linha \" na tabela de entrada e na tabela atual";
        //           "As colunas numéricas de \" Coluna para Linha \" devem ser do mesmo tipo, verifique a configura??o \"Coluna para Linha \" na tabela de entrada e na tabela atual"

        String tar = "\"\\u5217\\u8F6C\\u884C\"\\u7684\\u6570\\u503C\\u5217\\u5FC5\\u987B\\u662F\\u540C\\u4E00\\u7C7B\\u578B\\uFF0C\\u8BF7\\u68C0\\u67E5\\u8F93\\u5165\\u8868\\u4E0E\\u5F53\\u524D\\u8868\\u4E2D\\u7684\"\\u5217\\u8F6C\\u884C\"\\u8BBE\\u7F6E";

        System.err.println("第三种-----toCharArray");
        char[] chars = src.toCharArray();
        StringBuffer stringBuffer = new StringBuffer();
        for (char aChar : chars) {
            System.err.println("char字符:"+aChar);
            String x = Integer.toHexString(aChar);
            if (x.length() <= 2) {
                x = "\\u00" + x;
            } else {
                x = "\\u" + x;
            }
            stringBuffer.append(x);
        }
        System.out.println(stringBuffer);
    }
}
