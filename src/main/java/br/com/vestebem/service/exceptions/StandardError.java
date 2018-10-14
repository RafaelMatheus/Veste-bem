package br.com.vestebem.service.exceptions;

public class StandardError {
	private Integer statusHttp;
	private String mensagem;
	private long timeStamp;

	public StandardError(Integer statusHttp, String mensagem, long timeStamp) {
		super();
		this.statusHttp = statusHttp;
		this.mensagem = mensagem;
		this.timeStamp = timeStamp;
	}

	public Integer getStatusHttp() {
		return statusHttp;
	}

	public void setStatusHttp(Integer statusHttp) {
		this.statusHttp = statusHttp;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

}
