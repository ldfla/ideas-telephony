/*
 * Copyright 2023-present, Leandro Figueiredo
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE.md file in the root directory of this source tree.
 *
 */

package la.ldf.ideastelephony.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "SIP_EVENT_TYPE")
public class SipEventType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sipEventTypeId;

    @Column(name = "DESCRIPTION")
    private String sipEventDescription;

    @Column(name = "RFC_REFERENCE")
    private String rfcReference;

    public SipEventType() {
    }

    public SipEventType(String sipEventDescription, String rfcReference) {
        this.sipEventDescription = sipEventDescription;
        this.rfcReference = rfcReference;
    }

    public Long getSipEventTypeId() {
        return sipEventTypeId;
    }

    public void setSipEventTypeId(Long sipEventTypeId) {
        this.sipEventTypeId = sipEventTypeId;
    }

    public String getSipEventDescription() {
        return sipEventDescription;
    }

    public void setSipEventDescription(String sipEventDescription) {
        this.sipEventDescription = sipEventDescription;
    }

    public String getRfcReference() {
        return rfcReference;
    }

    public void setRfcReference(String rfcReference) {
        this.rfcReference = rfcReference;
    }

}
