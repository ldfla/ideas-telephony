/*
 * Copyright 2023-present, Leandro Figueiredo
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE.md file in the root directory of this source tree.
 *
 */

package la.ldf.ideastelephony.service;

import jakarta.transaction.Transactional;
import la.ldf.ideastelephony.entity.SipEvent;
import la.ldf.ideastelephony.repository.SipEventRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SipEventService {
    private SipEventRepository sipEventRepository;

    public SipEventService(SipEventRepository sipEventRepository) {
        this.sipEventRepository = sipEventRepository;
    }

    @Transactional
    public SipEvent push(SipEvent sipEvent) {
        return sipEventRepository.save(sipEvent);
    }

    public List<SipEvent> list() {
        Sort sort = Sort.by("timestamp").descending();
        return sipEventRepository.findAll(sort);
    }

    public SipEvent update(SipEvent sipEvent) {
        return sipEventRepository.save(sipEvent);
    }

    public List<SipEvent> delete(Long id) {
        sipEventRepository.deleteById(id);
        return list();
    }

}
