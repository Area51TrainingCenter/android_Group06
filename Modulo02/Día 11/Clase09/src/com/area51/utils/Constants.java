package com.area51.utils;

public class Constants {

	public static String APPversion = "1.0";

	public static String APIurl = "http://www.segundoacosta.com/";
	public static String APIversion = "area51api/";
	public static String APIsectionValidaUsuario = "validarusuario.php?";
	public static String APIvarUsuario = "&correoUsuario=";
	public static String APIvarClave = "&claveUsuario=";
	// VALORES DEVUELTOS POR EL API REST
	public static String APIResponse = "response";
	public static String APIResponseVal = "OK";
	public static String APIData = "data";
	public static String APIDataVal = "";

	public static String APIDataidusuaruio = "idusuario";
	public static String APIDatacorreo = "correo";
	public static String APIDatanombre = "nombre";

	public static String DBname = "gruporedbull.db";
	public static int DBversion = 1;

	public static String TableName = "usuario";
	public static String ColidUsuario = "idUsuario";
	public static String ColnameUsuario = "nameUsuario";
	public static String ColcorreoUsuario = "correoUsuario";

	public static int ColidUsuarioIndex = 0;
	public static int ColnameUsuarioIndex = 0;
	public static int ColcorreoUsuarioIndex = 0;

	public static String createTable = "CREATE TABLE " + TableName + "("
			+ ColidUsuario + " INTEGER PRIMARY KEY AUTOINCREMENT," + ColnameUsuario
			+ " text," + ColcorreoUsuario + " text)";

	public static String dropTable = "DROP TABLE " + TableName;

	/*
	 * { "response": "OK", "data": { valor:"ERROR", tipo:"CLAVE, USUARIO",
	 * mensaje:"Ingrese nuevos datos", codigo:"PEPITO1234"
	 * 
	 * "idusuario": "1", "correo": "segu19@gmail.com", "nombre":
	 * "Segundo Acosta Delgado" } }
	 * &correoUsuario=segu19@gmail.com&claveUsuario=segu1234
	 */

}
