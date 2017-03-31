package com.flowfact.bufimg;

import java.util.ArrayList;
import java.util.List;

public class Body {

	public List<String> getStand() {
		List<String> p = new ArrayList<String>();

		p.add("2-98");
		p.add("3-98");
		p.add("4-98");
		p.add("5-98");
		p.add("3-97");
		p.add("4-97");
		p.add("5-97");
		p.add("6-97");
		p.add("4-96");
		p.add("5-96");
		p.add("6-96");
		p.add("7-96");
		p.add("5-95");
		p.add("6-95");
		p.add("7-95");
		p.add("8-95");
		p.add("6-94");
		p.add("7-94");
		p.add("8-94");
		p.add("9-94");
		p.add("7-93");
		p.add("8-93");
		p.add("9-93");
		p.add("10-93");

		p.add("8-92");
		p.add("9-92");
		p.add("10-92");
		p.add("11-92");
		p.add("9-91");
		p.add("10-91");
		p.add("11-91");
		p.add("12-91");
		p.add("10-90");
		p.add("11-90");
		p.add("12-90");
		p.add("13-90");
		p.add("11-89");
		p.add("12-89");
		p.add("13-89");
		p.add("14-89");
		p.add("12-88");
		p.add("13-88");
		p.add("14-88");
		p.add("15-88");
		p.add("13-87");
		p.add("14-87");
		p.add("15-87");
		p.add("16-87");

		p.add("14-88");
		p.add("15-88");
		p.add("16-88");
		p.add("17-88");
		p.add("15-89");
		p.add("16-89");
		p.add("17-89");
		p.add("18-89");

		p.add("16-90");
		p.add("17-90");
		p.add("18-90");
		p.add("19-90");

		p.add("17-91");
		p.add("18-91");
		p.add("19-91");
		p.add("20-91");

		p.add("18-92");
		p.add("19-92");
		p.add("20-92");
		p.add("21-92");

		p.add("19-93");
		p.add("20-93");
		p.add("21-93");
		p.add("22-93");

		p.add("20-94");
		p.add("21-94");
		p.add("22-94");
		p.add("23-94");

		p.add("21-95");
		p.add("22-95");
		p.add("23-95");
		p.add("24-95");

		p.add("22-96");
		p.add("23-96");
		p.add("24-96");
		p.add("25-96");

		p.add("23-97");
		p.add("24-97");
		p.add("25-97");
		p.add("26-97");

		p.add("24-98");
		p.add("25-98");
		p.add("26-98");
		p.add("27-98");

		return p;
	}

	public List<String> getLinkerBalken() {
		List<String> p = new ArrayList<String>();

		for (int a = 86; a > 20; a--) {
			p.add("13-" + a);
			p.add("14-" + a);
			p.add("15-" + a);
			p.add("16-" + a);
		}

		return p;
	}

	public List<String> getOberenBalken() {
		List<String> p = new ArrayList<String>();

		for (int a = 17; a < 80; a++) {
			p.add(a + "-21");
			p.add(a + "-22");
			p.add(a + "-23");
			p.add(a + "-24");
		}

		return p;
	}

	public List<String> getStütze() {
		List<String> p = new ArrayList<String>();

		p.add("17-43");

		p.add("17-42");
		p.add("18-42");

		p.add("17-41");
		p.add("18-41");
		p.add("19-41");

		p.add("17-40");
		p.add("18-40");
		p.add("19-40");
		p.add("20-40");

		p.add("18-39");
		p.add("19-39");
		p.add("20-39");
		p.add("21-39");

		p.add("19-38");
		p.add("20-38");
		p.add("21-38");
		p.add("22-38");

		p.add("19-38");
		p.add("20-38");
		p.add("21-38");
		p.add("22-38");

		p.add("20-37");
		p.add("21-37");
		p.add("22-37");
		p.add("23-37");

		p.add("21-36");
		p.add("22-36");
		p.add("23-36");
		p.add("24-36");

		p.add("22-35");
		p.add("23-35");
		p.add("24-35");
		p.add("25-35");

		p.add("23-34");
		p.add("24-34");
		p.add("25-34");
		p.add("26-34");

		p.add("24-33");
		p.add("25-33");
		p.add("26-33");
		p.add("27-33");

		p.add("25-32");
		p.add("26-32");
		p.add("27-32");
		p.add("28-32");

		p.add("26-31");
		p.add("27-31");
		p.add("28-31");
		p.add("29-31");

		p.add("27-30");
		p.add("28-30");
		p.add("29-30");
		p.add("30-30");

		p.add("28-29");
		p.add("29-29");
		p.add("30-29");
		p.add("31-29");

		p.add("29-28");
		p.add("30-28");
		p.add("31-28");
		p.add("32-28");

		p.add("30-27");
		p.add("31-27");
		p.add("32-27");
		p.add("33-27");

		p.add("31-26");
		p.add("32-26");
		p.add("33-26");
		p.add("34-26");

		p.add("32-25");
		p.add("33-25");
		p.add("34-25");
		p.add("35-25");

		return p;
	}

	public List<String> getSeil() {

		List<String> p = new ArrayList<String>();

		for (int a = 25; a < 40; a++) {
			p.add("77-" + a);
			p.add("78-" + a);
		}

		return p;
	}

	public List<String> getKopf() {
		List<String> p = new ArrayList<String>();
		p.add("74-40");
		p.add("75-40");
		p.add("76-40");
		p.add("77-40");
		p.add("78-40");
		p.add("79-40");
		p.add("80-40");
		p.add("81-40");

		p.add("74-41");
		p.add("75-41");
		p.add("76-41");
		p.add("77-41");
		p.add("78-41");
		p.add("79-41");
		p.add("80-41");
		p.add("81-41");

		p.add("74-42");

		p.add("73-42");
		p.add("72-42");
		p.add("73-43");
		p.add("72-43");
		p.add("73-44");
		p.add("72-44");
		p.add("73-45");
		p.add("72-45");
		p.add("73-46");
		p.add("72-46");
		p.add("73-47");
		p.add("72-47");
		p.add("73-48");
		p.add("72-48");

		p.add("74-48");

		p.add("81-42");

		p.add("82-42");
		p.add("83-42");
		p.add("82-43");
		p.add("83-43");
		p.add("82-44");
		p.add("83-44");
		p.add("82-45");
		p.add("83-45");
		p.add("82-46");
		p.add("83-46");
		p.add("82-47");
		p.add("83-47");
		p.add("82-48");
		p.add("83-48");
		p.add("81-48");

		p.add("74-49");
		p.add("74-50");
		p.add("75-49");
		p.add("75-50");
		p.add("76-49");
		p.add("76-50");
		p.add("77-49");
		p.add("77-50");
		p.add("78-49");
		p.add("78-50");
		p.add("79-49");
		p.add("79-50");
		p.add("80-49");
		p.add("80-50");
		p.add("81-49");
		p.add("81-50");

		return p;
	}

	public List<String> getKörper() {
		List<String> p = new ArrayList<String>();

		for (int a = 51; a < 70; a++) {
			p.add("77-" + a);
			p.add("78-" + a);

		}

		return p;
	}

	public List<String> getLinkerArm() {
		List<String> p = new ArrayList<String>();

		for (int a = 76; a > 65; a--) {

			p.add(a + "-55");
			p.add(a + "-56");

		}

		return p;
	}

	public List<String> getRechterArm() {
		List<String> p = new ArrayList<String>();

		for (int a = 79; a < 90; a++) {

			p.add(a + "-55");
			p.add(a + "-56");

		}

		return p;
	}

	public List<String> getLinkesBein() {
		List<String> p = new ArrayList<String>();

		p.add("76-67");
		p.add("76-68");
		p.add("75-68");

		p.add("76-69");
		p.add("75-69");
		p.add("74-69");

		p.add("75-70");
		p.add("74-70");
		p.add("73-70");

		p.add("74-71");
		p.add("73-71");
		p.add("72-71");

		p.add("73-72");
		p.add("72-72");
		p.add("71-72");

		p.add("72-73");
		p.add("71-73");
		p.add("70-73");

		p.add("71-74");
		p.add("70-74");
		p.add("69-74");

		p.add("70-75");
		p.add("69-75");
		p.add("68-75");

		p.add("69-76");
		p.add("68-76");
		p.add("67-76");

		return p;
	}

	public List<String> getRechtesBein() {
		List<String> p = new ArrayList<String>();

		p.add("79-67");
		p.add("79-68");
		p.add("80-68");

		p.add("79-69");
		p.add("80-69");
		p.add("81-69");

		p.add("80-70");
		p.add("81-70");
		p.add("82-70");

		p.add("81-71");
		p.add("82-71");
		p.add("83-71");

		p.add("82-72");
		p.add("83-72");
		p.add("84-72");

		p.add("83-73");
		p.add("84-73");
		p.add("85-73");

		p.add("84-74");
		p.add("85-74");
		p.add("86-74");

		p.add("85-75");
		p.add("86-75");
		p.add("87-75");

		p.add("86-76");
		p.add("87-76");
		p.add("88-76");

		return p;
	}

}
