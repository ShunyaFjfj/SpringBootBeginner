package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * アプリケーション起動クラス
 * 
 * @author ys-fj
 *
 */
@SpringBootApplication
public class SampleWebApplication {

	/**
	 * アプリケーションを起動します。
	 * 
	 * @param args 起動に必要な引数
	 */
	public static void main(String[] args) {
		SpringApplication.run(SampleWebApplication.class, args);
	}

}
