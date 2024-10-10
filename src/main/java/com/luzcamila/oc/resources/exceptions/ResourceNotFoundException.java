package com.luzcamila.oc.resources.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String message) {
        super(message); // Chama o construtor da classe pai (RuntimeException)
    }
}
