package org.example.videoclub.services;

import org.springframework.stereotype.Service;

import java.text.Normalizer;

@Service
public class StringService {

    public String toCamelCase(String s){

        StringBuilder sb = new StringBuilder();
        Boolean esMayuscula = false;

        for(int i = 0; i < s.length(); i++){

            if(Character.isLetter(s.charAt(i))){
                char car = s.charAt(i);

                if(esMayuscula){
                    sb.append(Character.toUpperCase(car));
                } else {
                    sb.append(Character.toLowerCase(car));
                }

                esMayuscula = false;
            } else {
                esMayuscula = true;
            }

        }

        return sb.toString();
    }

    public String toPascalCase(String s){

        StringBuilder sb = new StringBuilder();
        Boolean esMayuscula = true;

        for(int i = 0; i < s.length(); i++){

            if(Character.isLetter(s.charAt(i))){
                char car = s.charAt(i);

                if(esMayuscula){
                    sb.append(Character.toUpperCase(car));
                } else {
                    sb.append(Character.toLowerCase(car));
                }

                esMayuscula = false;
            } else {
                esMayuscula = true;
            }

        }

        return sb.toString();
    }

    public String toSnakeCase(String s){

        s = s.toLowerCase();

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < s.length(); i++){

            if(s.charAt(i) >= 97 && s.charAt(i) <= 122){
                sb.append(s.charAt(i));
            } else if(s.charAt(i) == 32 || s.charAt(i) == 95){
                sb.append("_");
            }

        }

        return sb.toString();
    }

    public String eliminarDiacriticos(String s){

        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");

        return s;
    }



}
