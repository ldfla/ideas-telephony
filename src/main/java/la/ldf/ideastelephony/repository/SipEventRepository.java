/*
 * Copyright 2023-present, Leandro Figueiredo
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE.md file in the root directory of this source tree.
 *
 */

package la.ldf.ideastelephony.repository;

import la.ldf.ideastelephony.entity.SipEvent;
import org.springframework.data.jpa.repository.JpaRepository;
public interface SipEventRepository extends JpaRepository<SipEvent, Long> {


}
