# Email-Sending-CLI-Service
A terminal-first Java CLI tool for sending personalised emails via SMTP. Supports multiple recipients, name/company–based templating and attachments, all without any environment variables or GUI.

# mailctl

`mailctl` is a minimal CLI tool for sending **personalized emails via SMTP**.  
It is built for developers who want a simple, scriptable way to send custom emails directly from the command line—without GUIs, environment variables, or background services.

---

## Features

- Unix-style CLI (`m` command)
- Send emails to one or multiple recipients
- Per-recipient personalization (name + company)
- Built-in default email template
- Optional custom body override
- Attachments support
- Interactive SMTP credential prompt (no env vars required)

---

## Prerequisites

- macOS or Linux
- Java 17+
- Maven

---

# Setup

## 1. Clone the Repository

Run this in your local terminal:

`git clone https://github.com/<your-username>/mailctl.git`
`cd mailctl`

## 2. Build the Project

Run this from the project root (mailctl/):

`mvn clean package`

This generates:

`target/mailctl-1.0-SNAPSHOT.jar`

## 3. Make the Launcher Executable

Still in the project root:

`chmod +x m`

## 4. Run the CLI on your terminal

`./m`

You should see a welcome message. 

## 5.Sending an email 

The following command should be entered: 

`./m send \
  -t alice@example.com,bob@example.com \
  -n Alice,Bob \
  -c "Acme Corp","Beta Tech" \
  -s "Application for Software Engineer" \
  -a "/Users/yourname/Documents/resume.pdf" `

## 6. SMTP Configuration (Important)
### Gmail Users
Gmail does not allow normal account passwords for SMTP. You must use an App Password.
Creating a Gmail App Password: 
  - Go to Google Account → Security
  - Enable 2-Step Verification
  - Open App passwords
  - Create a password for:
    App: Mail
    Device: Mac
  - Copy the generated 16-character password
    This password can be reused. You do not need to regenerate it every run, 

When prompted in the terminal:

`SMTP Host: smtp.gmail.com`

`SMTP Port: 587`

`Email Username: your_email@gmail.com`

`Email App Password: <your app password>`



## What Happens Internally
When you run `./m` send, you are prompted in the terminal for:
`SMTP host
SMTP port
Email username
Email app password (hidden input)`
The tool:
1. Resolves recipients
2. Applies personalization
3. Builds email content
4. Sends emails sequentially
5. Prints success or failure per recipient

## Email Template Behavior
- If no `--body` is provided, a built-in default template is used.
- The template automatically replaces:
  [Recipient Name]
  [Company Name]
- Each recipient receives a customized email.
- If `--body` is provided, the default template is skipped.
- User can edit the default template according to their use case as it has been hard- coded. 


## Future Scope
The following ideas were intentionally excluded to keep the tool simple:
- Advanced rate limiting
- Summary logs
- Retry queues
- HTML templating engines
- OAuth-based SMTP authentication
- Config files instead of prompts
- Homebrew/global installation
- Background daemon mode
- Analytics dashboards


