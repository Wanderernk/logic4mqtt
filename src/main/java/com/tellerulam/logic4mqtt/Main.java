package com.tellerulam.logic4mqtt;

import java.util.*;
import java.util.logging.*;

import org.eclipse.paho.client.mqttv3.*;

public class Main
{
	static public final String version="0.2";

	static final Timer t=new Timer(true);

	public static void main(String[] args) throws MqttException
	{
		/*
		 * Interpret all command line arguments as property definitions (without the hm2mqtt prefix)
		 */
		for(String s:args)
		{
			String sp[]=s.split("=",2);
			if(sp.length!=2)
			{
				System.out.println("Invalid argument (no '='): "+s);
				System.exit(1);
			}
			System.setProperty("logic4mqtt."+sp[0],sp[1]);
		}
		Logger.getLogger(Main.class.getName()).info("logic4mqtt V"+version+" (C) 2015 Oliver Wagner <owagner@tellerulam.com>");
		MQTTHandler.init();
		String scriptDirs=System.getProperty("logic4mqtt.scripts.dir","scripts");
		for(String scriptDir:scriptDirs.split(":"))
			ScriptScanner.doScan(scriptDir);
	}
}
