/*
 * Copyright 2023-present, Leandro Figueiredo
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE.md file in the root directory of this source tree.
 *
 */

package la.ldf.ideastelephony;

import javax.sip.*;
import javax.sip.message.*;
import java.util.Properties;

public class SIPListener implements SipListener {
    private SipStack sipStack;
    private SipProvider sipProvider;

    public SIPListener() {
        try {
            SipFactory sipFactory = SipFactory.getInstance();
            sipFactory.setPathName("gov.nist");
            Properties properties = new Properties();

            //TODO: Configurar as propriedades do SIP stack
            properties.setProperty("javax.sip.STACK_NAME", "stack");

            sipStack = sipFactory.createSipStack(properties);
            //TODO: Definir IP automaticamente
            ListeningPoint listeningPoint = sipStack.createListeningPoint("0.0.0.0", 5055, "your-transport-protocol");

            sipProvider = sipStack.createSipProvider(listeningPoint);
            sipProvider.addSipListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processRequest(RequestEvent requestEvent) {
        // Handle incoming SIP requests
        Request request = requestEvent.getRequest();
        //TODO: Implementar logica de requests. Filtro de eventos de Incoming
        //Envio de RE-INVITE. Abertura da porta RTP
    }

    @Override
    public void processResponse(ResponseEvent responseEvent) {
        // Handle incoming SIP responses
        Response response = responseEvent.getResponse();
        //TODO: Implementar logica de responses. Confirmar que as chamadas estão ativas
    }

    @Override
    public void processTimeout(TimeoutEvent timeoutEvent) {
        // Handle timeout events
        //TODO: Implementar ação para rechamar em caso de Timeout
    }

    @Override
    public void processIOException(IOExceptionEvent ioExceptionEvent) {
        //TODO: Tratar erros

    }

    @Override
    public void processTransactionTerminated(TransactionTerminatedEvent transactionTerminatedEvent) {
        //TODO: Tratar desconexão
    }

    @Override
    public void processDialogTerminated(DialogTerminatedEvent dialogTerminatedEvent) {
        //TODO: Tratar Fim do diálogo. Limpar Fila. Diminuir HEAP
    }
}
