//FOR FURTHER REFERENCE
package la.ldf.ideastelephony;


import javax.sip.*;
import javax.sip.address.Address;
import javax.sip.address.AddressFactory;
import javax.sip.header.*;
import javax.sip.message.MessageFactory;
import javax.sip.message.Request;
import javax.swing.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TooManyListenersException;

public class Demo {
    private String ip;
    private SipFactory sipFactory;
    private Properties properties;
    private AddressFactory address;
    private SipStack sipStack;
    private SipProvider sipProvider;
    private MessageFactory messageFactory;
    private AddressFactory addressFactory;
    private HeaderFactory headerFactory;
    private ListeningPoint listeningPoint;
    private int port;
    private String protocol;
    private Address contactAddress;
    private Header contactHeader;
    private String tag;


    private void onOpen(java.awt.event.WindowEvent evt) {

        try {
            // Get the local IP address.
            this.ip = "185.224.184.100";
            // Create the SIP factory and set the path name.
            this.sipFactory = SipFactory.getInstance();
            this.sipFactory.setPathName("gov.nist");
            // Create and set the SIP stack properties.
            this.properties = new Properties();

            this.properties.put("javax.sip.STACK_NAME", "Mobicents-SIP-Servlets");
            this.properties.setProperty("javax.sip.TRACE_LEVEL", "32");
            //this.properties.setProperty("gov.nist.javax.sip.STACK_NAME", "Mobicents-SIP-Servlets");
            this.properties.setProperty("javax.sip.IP_ADDRESS", this.ip);
            this.properties.setProperty("javax.sip.MAX_MESSAGE_SIZE", "1048576");
            this.properties.setProperty("javax.sip.DEBUG_LOG", "xDebug.txt");
            this.properties.setProperty("javax.sip.SERVER_LOG", "xLog.txt");

            // Drop the client connection after we are done with the transaction.
            this.properties.setProperty("javax.sip.CACHE_CLIENT_CONNECTIONS", "false");

            this.properties.setProperty("javax.sip.LOG_MESSAGE_CONTENT", "true");

            this.properties.setProperty("javax.sip.AUTOMATIC_DIALOG_SUPPORT", "off");
            this.properties.setProperty("javax.sip.DELIVER_UNSOLICITED_NOTIFY", "true");
            this.properties.setProperty("javax.sip.THREAD_POOL_SIZE", "64");
            this.properties.setProperty("javax.sip.REENTRANT_LISTENER", "true");
            this.properties.setProperty("javax.sip.MAX_FORK_TIME_SECONDS", "0 ");
            this.properties.setProperty("javax.sip.AUTOMATIC_DIALOG_ERROR_HANDLING", "false");

            sipStack = sipFactory.createSipStack(properties);
            System.out.println("sipStack = " + sipStack);

            // Create the SIP stack.
            this.sipStack = this.sipFactory.createSipStack(this.properties);
            // Create the SIP message factory.
            this.messageFactory = this.sipFactory.createMessageFactory();
            // Create the SIP header factory.
            this.headerFactory = this.sipFactory.createHeaderFactory();
            // Create the SIP address factory.
            this.addressFactory = this.sipFactory.createAddressFactory();
            // Create the SIP listening point and bind it to the local IP address, port and protocol.
            this.listeningPoint = this.sipStack.createListeningPoint(this.ip, this.port, this.protocol);
            // Create the SIP provider.
            this.sipProvider = this.sipStack.createSipProvider(this.listeningPoint);
            // Add our application as a SIP listener.
            //TODO: Fix it this.sipProvider.addSipListener(this);
            // Create the contact address used for all SIP messages.
            this.contactAddress = this.addressFactory.createAddress("sip:" + this.ip + ":" + this.port);
            // Create the contact header used for all SIP messages.
            this.contactHeader = this.headerFactory.createContactHeader(contactAddress);

            // Display the local IP address and port in the text area.
            System.err.println("Local address: " + this.ip + ":" + this.port + "\n");
        } catch (ParseException e) {
            // If an error occurs, display an error message box and exit.
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        } catch (TooManyListenersException e) {
            // If an error occurs, display an error message box and exit.
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        } catch (InvalidArgumentException e) {
            // If an error occurs, display an error message box and exit.
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        } catch (ObjectInUseException e) {
            // If an error occurs, display an error message box and exit.
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        } catch (PeerUnavailableException e) {
            // If an error occurs, display an error message box and exit.
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        } catch (TransportNotSupportedException e) {
            // If an error occurs, display an error message box and exit.
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }
    }

    private void onRegisterStateless(java.awt.event.ActionEvent evt) {
        // A method called when you click on the "Reg (SL)" button.
        try {
            // Get the destination address from the text field.
            Address addressTo = this.addressFactory.createAddress("0.0.0.0");
            // Create the request URI for the SIP message.
            javax.sip.address.URI requestURI = addressTo.getURI();

            // Create the SIP message headers.
            // The "Via" headers.

            ArrayList viaHeaders = new ArrayList();
            ViaHeader viaHeader = this.headerFactory.createViaHeader(this.ip, this.port, "udp", null);
            viaHeaders.add(viaHeader);
            // The "Max-Forwards" header.
            MaxForwardsHeader maxForwardsHeader = this.headerFactory.createMaxForwardsHeader(70);
            // The "Call-Id" header.
            CallIdHeader callIdHeader = this.sipProvider.getNewCallId();
            // The "CSeq" header.
            // CSeqHeader cSeqHeader = this.headerFactory.createCSeqHeader(1L, "REGISTER");
            CSeqHeader cSeqHeader = this.headerFactory.createCSeqHeader(1L, Request.MESSAGE);

            // The "From" header.
            FromHeader fromHeader = this.headerFactory.createFromHeader(this.contactAddress, String.valueOf(this.tag));
            // The "To" header.
            ToHeader toHeader = this.headerFactory.createToHeader(addressTo, null);

            // Create the REGISTER request.
            Request request = this.messageFactory.createRequest(
                    requestURI,
                    "REGISTER",
                    callIdHeader,
                    cSeqHeader,
                    fromHeader,
                    toHeader,
                    viaHeaders,
                    maxForwardsHeader);
            // Add the "Contact" header to the request.
            request.addHeader(contactHeader);

            // Send the request statelessly through the SIP provider.
            this.sipProvider.sendRequest(request);

            // Display the message in the text area.
            System.err.println(
                    "Request sent:\n" + request.toString() + "\n\n");
        } catch (ParseException e) {
            // If an error occurred, display the error.
            System.err.println("Request sent failed: " + e.getMessage() + "\n");
        } catch (InvalidArgumentException e) {
            // If an error occurred, display the error.
            System.err.println("Request sent failed: " + e.getMessage() + "\n");
        } catch (SipException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        // If an error occurred, display the error.

    }
}
