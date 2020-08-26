package com.tempest.math;

import java.security.Provider;

public final class CryptoProvider extends Provider {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CryptoProvider() {
        super("Crypto", 1.0, "HARMONY (SHA1 digest; SecureRandom; SHA1withDSA signature)");
        put("SecureRandom.SHA1PRNG","org.apache.harmony.security.provider.crypto.SHA1PRNG_SecureRandomImpl");
        put("SecureRandom.SHA1PRNG ImplementedIn", "Software");
    }
}
