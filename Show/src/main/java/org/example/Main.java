package org.example;

/**
 * Разбор пьесы по ролям
 */
public class Main {
    public static void main(String[] args) {
        String[] roles = {
                "Городничий", "Аммос Федорович",
                "Артемий Филиппович",
                "Лука Лукич"};
        String[] textLines = {
                "Городничий: Я пригласил вас, господа, с тем, чтобы сообщить вам пренеприятное известие: к нам едет ревизор.",
                "Аммос Федорович: Как ревизор?",
                "Артемий Филиппович: Как ревизор?",
                "Городничий: Ревизор из Петербурга, инкогнито. И еще с секретным предписаньем.",
                "Аммос Федорович: Вот те на!",
                "Артемий Филиппович: Вот не было заботы, так подай!",
                "Лука Лукич: Господи боже! еще и с секретным предписаньем!"};

        System.out.println(printTextPerRole(roles, textLines));
    }

    private static String printTextPerRole(String[] roles, String[] textLines) {
        var stringBuilderOut = new StringBuilder();
        for (var role = 0; role < roles.length; role++) {
            stringBuilderOut.append(roles[role]).append(":").append('\n');
            for (var line = 0; line < textLines.length; line++) {
                var text = textLines[line];
                if (text.startsWith(roles[role] + ":")) {
                    var phrase = text.replaceFirst(roles[role] + ":", String.valueOf(line + 1) + ")");
                    stringBuilderOut.append(phrase).append('\n');
                }
            }
            stringBuilderOut.append('\n');
        }
        return String.valueOf(stringBuilderOut);
    }


}