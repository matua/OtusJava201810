import org.apache.commons.cli.*;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Program {
    public static void printHelp(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter writer = new PrintWriter(System.out);
        helpFormatter.printHelp(writer, 100, "Options", "-----Help-----",
                options, 4, 6, "footer", true);
        writer.flush();
    }

    public static void main(String[] args) {

        Options options = new Options();

        //program.jar -login login
        Option optionLogin = new Option("l", "login", true, "Login Name");
        optionLogin.setArgs(1); //максимальное количество аргументов
        optionLogin.setOptionalArg(false); //являются ли аргументы необязательными
        optionLogin.setArgName("login"); //название опции в справке

        Option optionPassword = new Option("p", "password", true, "Password Name");
        optionPassword.setArgs(1); //количество аргументов
        optionPassword.setOptionalArg(false); //являются ли аргументы необязательными
        optionPassword.setArgName("password"); //название опции в справке

        Option optionE = new Option("e", "eee", true, "EEE Name");
        optionE.setArgs(5); //количество аргументов
        optionE.setOptionalArg(false); //являются ли аргументы необязательными
        optionE.setArgName("eee"); //название опции в справке

        Option optionHelp = new Option("h", "help", false, "Help description");
        optionHelp.setArgs(0); //количество аргументов
        optionHelp.setOptionalArg(true); //являются ли аргументы необязательными
        optionHelp.setArgName("help"); //название опции в справке*/

        Option optionVersion = new Option("v", "version", false, "Version description");
        optionVersion.setArgs(0); //количество аргументов
        optionVersion.setOptionalArg(true); //являются ли аргументы необязательными
        optionVersion.setArgName("version"); //название опции в справке*/

        OptionGroup optionGroupHelpVersion = new OptionGroup();
        optionGroupHelpVersion.addOption(optionHelp);
        optionGroupHelpVersion.addOption(optionVersion);

        options.addOption(optionLogin);
        options.addOption(optionPassword);
        options.addOptionGroup(optionGroupHelpVersion);
        options.addOption(optionE);

        CommandLineParser commandLineParser = new PosixParser();
        try {
            CommandLine commandLine = commandLineParser.parse(options, args);
            if (commandLine.hasOption("l") && commandLine.hasOption("p")) {
                System.out.println("Одновременно" + commandLine.getOptionValue("l") + " " + commandLine.getOptionValue("p"));
            } else {
                if (commandLine.hasOption("l")) {
                    System.out.println(commandLine.getOptionValue("l"));
                }
                if (commandLine.hasOption("h"))
                    printHelp(options);
                if (commandLine.hasOption("p"))
                    System.out.println(commandLine.getOptionValue("p"));
                if (commandLine.hasOption("v")) {
                    System.out.println("v 1.0");
                }
                if(commandLine.hasOption("e")){
                    String[] es = commandLine.getOptionValues("e");
                    for (String e : es)
                        System.out.print(e + "  ");
                    System.out.println("\n" + Arrays.toString(es));
                }
            }
        } catch (ParseException e) {
            System.out.println("Usage -h");
        }

        Scanner s = new Scanner(System.in);
        while (true) {
            String command = s.nextLine();
            String[] s1 = command.split(" ");
            if (command.contains("-q"))
                return;
            try {
                CommandLine commandLine = commandLineParser.parse(options, s1);
                if (commandLine.hasOption("l"))
                    System.out.println(commandLine.getOptionValue("l"));

                if (commandLine.hasOption("-h"))
                    printHelp(options);
            } catch (ParseException e) {
                System.out.println("Usage -h");
            }
        }

    }
}