/*
 * Copyright (c) 2018, Choucair Testing S.A. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */

package satrack.com.utilidades;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MainPostBuildActions {

	public static void main(String[] args) {
		actualizarCssEvidencias();
	}

	private static void actualizarCssEvidencias() {
		String file = "target/site/serenity/css/core.css";
		String cssToAdd = "img[alt='evidencia']{ max-width: 100%;width: 300px;}";

		try (FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.print(cssToAdd);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
