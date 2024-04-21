package br.com.magna.entities;

public class Calc {

	private Double[] coordinatesOne;
	private Double[] coordinatesTwo;
	
	public Calc(Double[] coordinatesOne, Double[] coordinatesTwo) {
		this.coordinatesOne = coordinatesOne;
		this.coordinatesTwo = coordinatesTwo;
	}
	
	public String execute() {
		StringBuilder sb = new StringBuilder();
		
		Double[] differenceBetweenCoordinates = calculateDifferenceBetweenCoordinates(coordinatesOne, coordinatesTwo);

		Double differenceBetweenFaceOneXAndFaceTwoX = differenceBetweenCoordinates[0];
		Double differenceBetweenFaceOneYAndFaceTwoY = differenceBetweenCoordinates[1];
		Double differenceBetweenFaceOneZAndFaceTwoZ = differenceBetweenCoordinates[2];

		Double[] differencesToThePowerOfTwo = calculateDifferencesToThePowerOfTwo(differenceBetweenFaceOneXAndFaceTwoX,
				differenceBetweenFaceOneYAndFaceTwoY, differenceBetweenFaceOneZAndFaceTwoZ);

		Double differenceXToThePowerOfTwo = differencesToThePowerOfTwo[0];
		Double differenceYToThePowerOfTwo = differencesToThePowerOfTwo[1];
		Double differenceZToThePowerOfTwo = differencesToThePowerOfTwo[1];

		Double sum = sumDifferencesToThePowerOfTwo(differenceXToThePowerOfTwo, differenceYToThePowerOfTwo,
				differenceZToThePowerOfTwo);

		Double squareRootFromSum = calculateSumSquareRoot(sum);

		sb.append("Dox = (faceOneX,faceOneY,faceOneZ) - (faceTwoX, faceTwoY, faceTwoZ)\n");

		sb.append("Dox = " + differenceBetweenFaceOneXAndFaceTwoX + ", " + differenceBetweenFaceOneYAndFaceTwoY
				+ ", " + differenceBetweenFaceOneZAndFaceTwoZ + "\n");

		sb.append("Dox = sqrt(" + differenceBetweenFaceOneXAndFaceTwoX + "^2 " + "+ "
				+ differenceBetweenFaceOneYAndFaceTwoY + "^2 " + "+ " + differenceBetweenFaceOneZAndFaceTwoZ + "^2)\n");

		sb.append("Dox = sqrt(" + String.format("%.2f", differenceXToThePowerOfTwo) + " + "
				+ String.format("%.2f", differenceYToThePowerOfTwo) + " + "
				+ String.format("%.2f", differenceZToThePowerOfTwo) + "\n");

		sb.append("Dox = sqrt(" + String.format("%.2f", sum) + ")\n");
		sb.append("Dox = " + String.format("%.3f", squareRootFromSum));
		
		return sb.toString();
	}

	public Double[] calculateDifferenceBetweenCoordinates(Double[] coordinatesOne, Double[] coordinatesTwo) {
		Double faceOneX = coordinatesOne[0];
		Double faceOneY = coordinatesOne[1];
		Double faceOneZ = coordinatesOne[2];

		Double faceTwoX = coordinatesTwo[0];
		Double faceTwoY = coordinatesTwo[0];
		Double faceTwoZ = coordinatesTwo[0];

		Double faceOneXMinusFaceTwoX = faceOneX - faceTwoX;
		Double faceOneYMinusFaceTwoY = faceOneY - faceTwoY;
		Double faceOneZMinusFaceTwoZ = faceOneZ - faceTwoZ;

		System.out.println("Dox = (" + faceOneX + " , " + faceOneY + " , " + faceOneZ + ") " + "- (" + faceTwoX + " , "
				+ faceTwoY + " , " + faceTwoZ + ")");

		return new Double[] { faceOneXMinusFaceTwoX, faceOneYMinusFaceTwoY, faceOneZMinusFaceTwoZ };
	}

	public Double[] calculateDifferencesToThePowerOfTwo(Double differenceBetweenFaceOneXAndFaceTwoX,
			Double differenceBetweenFaceOneYAndFaceTwoY, Double differenceBetweenFaceOneZAndFaceTwoZ) {

		Double differenceBetweenFaceOneXAndFaceTwoXToPowerTwo = Math.pow(differenceBetweenFaceOneXAndFaceTwoX, 2);
		Double differenceBetweenFaceOneYAndFaceTwoYToPowerTwo = Math.pow(differenceBetweenFaceOneYAndFaceTwoY, 2);
		Double differenceBetweenFaceOneZAndFaceTwoZToPowerTwo = Math.pow(differenceBetweenFaceOneZAndFaceTwoZ, 2);

		return new Double[] { differenceBetweenFaceOneXAndFaceTwoXToPowerTwo,
				differenceBetweenFaceOneYAndFaceTwoYToPowerTwo, differenceBetweenFaceOneZAndFaceTwoZToPowerTwo };
	}

	public Double sumDifferencesToThePowerOfTwo(Double differenceXToThePowerOfTwo, Double differenceYToThePowerOfTwo,
			Double differenceZToThePowerOfTwo) {
		return differenceXToThePowerOfTwo + differenceYToThePowerOfTwo + differenceZToThePowerOfTwo;
	}

	public Double calculateSumSquareRoot(Double sum) {
		return Math.sqrt(sum);
	}
}
