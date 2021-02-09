package fundamentos;

public class PrimeiroPrograma {
	
		final double Fator = 5/9.0;
		final double Ajuste = 32;
		float TempF = 86;
		double TempC = (TempF-Ajuste)*Fator;
		 
		void imprimir() {
		 System.out.println(TempC+"°C");
		}
}


//(°F-32)*