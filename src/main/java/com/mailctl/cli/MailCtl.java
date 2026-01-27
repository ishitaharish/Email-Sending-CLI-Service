package com.mailctl.cli;

import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
        name = "m",
        description = "MailCtl — a simple CLI for sending emails via SMTP",
        version = "m 1.0",
        mixinStandardHelpOptions = true,
        subcommands = {
                SendCommand.class
        }
)
public class MailCtl implements Runnable {

    @Override
    public void run() {
        // This runs when user types only: m
        System.out.println();
        System.out.println("MailCtl CLI");
        System.out.println("----------------------------");
        System.out.println("Send emails from the terminal.");
        System.out.println();
        System.out.println("Common commands:");
        System.out.println("  m send --help    Show send command options");
        System.out.println("  m --help         Show all commands");
        System.out.println();
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new MailCtl()).execute(args);
        System.exit(exitCode);
    }
}

