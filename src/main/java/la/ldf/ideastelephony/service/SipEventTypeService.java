/*
 * Copyright 2023-present, Leandro Figueiredo
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE.md file in the root directory of this source tree.
 *
 */

package la.ldf.ideastelephony.service;

import la.ldf.ideastelephony.entity.SipEventType;
import la.ldf.ideastelephony.repository.SipEventTypeRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SipEventTypeService {
    private final SipEventTypeRepository sipEventTypeRepository;

    public SipEventTypeService(SipEventTypeRepository sipEventTypeRepository) {
        this.sipEventTypeRepository = sipEventTypeRepository;
    }

    public List<SipEventType> list() {
        Sort sort = Sort.by("sipEventDescription").descending();
        System.err.println("Seeding");
        seed();

        return sipEventTypeRepository.findAll(sort);
    }

    private void seed(){
        sipEventTypeRepository.save(new SipEventType("call-completion","[RFC6910]"));
        sipEventTypeRepository.save(new SipEventType("certificate","[RFC6072]"));
        sipEventTypeRepository.save(new SipEventType("credential","[RFC6072]"));
        sipEventTypeRepository.save(new SipEventType("conference","[RFC4575]"));
        sipEventTypeRepository.save(new SipEventType("consent-pending-additions","[RFC5362]"));
        sipEventTypeRepository.save(new SipEventType("dialog","[RFC4235]"));
        sipEventTypeRepository.save(new SipEventType("http-monitor","[RFC5989]"));
        sipEventTypeRepository.save(new SipEventType("kpml","[RFC4730]"));
        sipEventTypeRepository.save(new SipEventType("load-control","[RFC7200]"));
        sipEventTypeRepository.save(new SipEventType("message-summary","[RFC3842]"));
        sipEventTypeRepository.save(new SipEventType("poc-settings","[RFC4354]"));
        sipEventTypeRepository.save(new SipEventType("presence","[RFC3856]"));
        sipEventTypeRepository.save(new SipEventType("reg","[RFC3680]"));
        sipEventTypeRepository.save(new SipEventType("refer","[RFC3515]"));
        sipEventTypeRepository.save(new SipEventType("session-spec-policy","[RFC6795]"));
        sipEventTypeRepository.save(new SipEventType("Siemens-RTP-Stats","[N/A]"));
        sipEventTypeRepository.save(new SipEventType("spirits-INDPs","[RFC3910]"));
        sipEventTypeRepository.save(new SipEventType("spirits-user-prof","[RFC3910]"));
        sipEventTypeRepository.save(new SipEventType("ua-profile","[RFC6080]"));
        sipEventTypeRepository.save(new SipEventType("vq-rtcpxr","[RFC6035]"));
        sipEventTypeRepository.save(new SipEventType("winfo","[RFC3857]"));
        sipEventTypeRepository.save(new SipEventType("xcap-diff","[RFC5875]"));
    }
}
