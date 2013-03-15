package com.kaimbe.gimmedadough.atm;

import com.kaimbe.gimmedadough.mediation.Mediator;
import com.kaimbe.gimmedadough.mediation.Proxy;

public class ATMController {
	private Proxy proxy;
	private Mediator mediator;
	
	public ATMController(Proxy proxy, Mediator mediator) {
		this.proxy = proxy;
		this.mediator = mediator;
	}
	
	/**
	 * @return the mediator
	 */
	public Mediator getMediator() {
		return mediator;
	}

	/**
	 * @return the proxy
	 */
	public Proxy getProxy() {
		return proxy;
	}
}
