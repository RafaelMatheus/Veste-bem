package br.com.vestebem.controller.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class Url {
	public static List<Integer> decodeIntList(String list){
		String[] aux = list.split(",");
		List<Integer> listaAux = new ArrayList<Integer>();
		for (int i = 0; i < aux.length; i++) {
			listaAux.add(Integer.parseInt(aux[i]));
		}
		return listaAux;
	}
	public static String decodeParam(String param) {
		try {
			return URLDecoder.decode(param, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return "";
		}
	}
}
