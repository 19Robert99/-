/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication51;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author robert.talabishka
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class MessageProcessing {

    private char[] code_string;
    private char[] decode_string;
    String alfavit = "ABCDEFGHIJKLMNOPQRSTUVWXYZ_,!?";
    char[] alf = alfavit.toCharArray();
    private String[][] mainArray = new String[6][5];
    private char[] contmes;

    int count = 0;

    ArrayList<String> mlist = new ArrayList<>();

    ArrayList<String> outcode = new ArrayList<>();

    public void enterKey(String key, String mess) {
        if (key == null) {
            key = "ROBERT";
        }
        code_string = key.toCharArray();
        contmes = mess.toCharArray();

        for (int i = 0; i < code_string.length; i++) {
            mlist.add(String.valueOf(code_string[i]));
        }
        for (int i = 0; i < alf.length; i++) {
            mlist.add(String.valueOf(alf[i]));
        }

        System.out.println(mlist);
        //Отбираем элементы
        List<String> list = mlist;
        List<String> uniqueElements
                = list
                        .stream()
                        .distinct()
                        .collect(Collectors.toList());

        System.out.println(uniqueElements);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++, count++) {
                mainArray[i][j] = uniqueElements.get(count);
            }
        }

        //Вывод главной матрицы       
        for (int i = 0; i < mainArray.length; i++) {
            for (int j = 0; j < mainArray[i].length; j++) {
                System.out.print(mainArray[i][j] + "\t");
            }
            System.out.println();
        }

        //Кодирование
        int point = 0;
        while (point != contmes.length) {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 5; j++) {

                    if (mainArray[i][j].equals(String.valueOf(contmes[point]))) {
                        outcode.add(Integer.toString(i));
                        outcode.add(Integer.toString(j));
                    }
                }
            }
            point++;
        }

        System.out.println(outcode);
        
        System.out.println("Зашифрованное сообщение:");   
        for (int i = 0; i < outcode.size(); i++) {       
            System.out.print(outcode.get(i));       
        }
        
        System.out.println();   
        

    }

    public void decode(String key, String mess) {
        if (key == null) {
            key = "ROBERT";
        }
        code_string = key.toCharArray();
        contmes = mess.toCharArray();

        for (int i = 0; i < code_string.length; i++) {
            mlist.add(String.valueOf(code_string[i]));
        }
        for (int i = 0; i < alf.length; i++) {
            mlist.add(String.valueOf(alf[i]));
        }

        System.out.println(mlist);
        //Отбираем элементы
        List<String> list = mlist;
        List<String> uniqueElements
                = list
                        .stream()
                        .distinct()
                        .collect(Collectors.toList());

        System.out.println(uniqueElements);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++, count++) {
                mainArray[i][j] = uniqueElements.get(count);
            }
        }

        //Вывод главной матрицы       
        for (int i = 0; i < mainArray.length; i++) {
            for (int j = 0; j < mainArray[i].length; j++) {
                System.out.print(mainArray[i][j] + "\t");
            }
            System.out.println();
        }

        //Декодирование
        int z = 0, x = 1;
        while (x <= contmes.length) {
            int p1 = Character.getNumericValue(contmes[z]);
            int p2 = Character.getNumericValue(contmes[x]);
            System.out.print(mainArray[p1][p2]);
            z += 2;
            x += 2;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Для кодирования сообщения введите 0, для декодирования введите 1:");
        int check = in.nextInt();
        switch (check) {

            case 0:
                System.out.println("Предупреждение! В качестве пробела использовать знак '_':");
                System.out.println("Введите сообщение:");
                String mess = in.next();
                System.out.println("Введите ключ:");
                String key = in.next();
                new MessageProcessing().enterKey(key, mess);
                break;
            case 1:
                System.out.println("Введите сообщение:");
                String mess1 = in.next();
                System.out.println("Введите ключ:");
                String key1 = in.next();
                new MessageProcessing().decode(key1, mess1);
                break;
        }
    }

}
