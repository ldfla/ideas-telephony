/*
 * Copyright 2023-present, Leandro Figueiredo
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE.md file in the root directory of this source tree.
 *
 */

package la.ldf.ideastelephony.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "SIP_EVENT")
public class SipEvent {
    @Id
    private int id;
    private Long sipEventTypeId;
    private Boolean active;
    private String createdBy;
    private java.util.Date timestamp;
    @JoinColumn(referencedColumnName = "SIP_EVENT_ID.ID", name="SIP_EVENT.sipEventId")

    public int getId() {
        return id;
    }

    public Long getSipEventTypeId() {
        return sipEventTypeId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
