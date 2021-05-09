package solver;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComplexNumbers {
    public static final ComplexNumbers ZERO = new ComplexNumbers(0d, 0d);
    public static final ComplexNumbers ONE = new ComplexNumbers(0d, 0d);
    private final double real;
    private final double imaginary;

    public ComplexNumbers(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public ComplexNumbers(String complexNumber) {
        Matcher matchRealPart = Pattern.compile("^-?[1-9]*\\d\\.?\\d*").matcher(complexNumber);
        if (matchRealPart.find() && !complexNumber.matches("^-?[1-9]*\\d*\\.?\\d*i$")) {
            this.real = Double.parseDouble(matchRealPart.group());
        } else {
            this.real = 0d;
        }

        Matcher matchImaginaryPart = Pattern.compile("-?[1-9]*\\d*\\.?\\d*i$").matcher(complexNumber);
        if (matchImaginaryPart.find()) {
            String imaginaryPart = matchImaginaryPart.group().replaceAll("-i", "-1").replaceAll("i", "");
            if (imaginaryPart.isEmpty()) {
                imaginaryPart = "1";
            }
            this.imaginary = Double.parseDouble(imaginaryPart);
        } else {
            this.imaginary = 0d;
        }
    }

    ComplexNumbers add(ComplexNumbers addend) {
        double realPart = this.real + addend.real;
        double imaginaryPart = this.imaginary + addend.imaginary;
        return new ComplexNumbers(realPart, imaginaryPart);
    }

    ComplexNumbers multiply(ComplexNumbers multiplicand) {
        double realPart = (real * multiplicand.real) + (imaginary * multiplicand.imaginary * -1d);
        double imaginaryPart = (real * multiplicand.imaginary) + (imaginary * multiplicand.real);
        return new ComplexNumbers(realPart, imaginaryPart);
    }

    ComplexNumbers divide(ComplexNumbers divisor) {
        ComplexNumbers conjugatedNumbers = divisor.conjugate();
        double denominator = 1d / (divisor.multiply(conjugatedNumbers).real);
        return multiply(conjugatedNumbers).multiplyByScalar(denominator);
    }

    ComplexNumbers negate() {
        return multiplyByScalar(-1d);
    }

    ComplexNumbers conjugate() {
        return new ComplexNumbers(real, imaginary * -1d);
    }

    ComplexNumbers multiplyByScalar(double scalar) {
        return new ComplexNumbers(real * scalar, imaginary * scalar);
    }

    @Override
    public String toString() {
        if (imaginary == 0d) {
            return Double.toString(real);
        } else {
            return (real == 0d ? "" : Double.toString(real)) +
                    (imaginary > 0 ? "+" : "") +
                    Double.toString(imaginary).replaceAll("^-?1$", "") + "i";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComplexNumbers that = (ComplexNumbers) o;
        return Objects.equals(real, that.real) &&
                Objects.equals(imaginary, that.imaginary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(real, imaginary);
    }
}
