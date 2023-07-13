/*
 * Copyright 2023-present, Leandro Figueiredo
 * All rights reserved.
 *
 * This source code is licensed under the Apache-2.0 license found in
 * the LICENSE.md file in the root directory of this source tree.
 *
 */

package la.ldf.ideastelephony;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IdeasTelephonyApplication {

	public static void main(String[] args) {
		new SIPListener();
		SpringApplication.run(IdeasTelephonyApplication.class, args);
	}

}