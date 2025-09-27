package main.extra;

public class Vector2D {
    public double x, y;

    public Vector2D() {
        this.x = 0.0;
        this.y = 0.0;
    }

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void add(Vector2D v) {
        this.x += v.x;
        this.y += v.y;
    }

    public void subtract(Vector2D v) {
        this.x -= v.x;
        this.y -= v.y;
    }

    public void multiply(Vector2D v) {
        this.x *= v.x;
        this.y *= v.y;
    }

    public void divide(Vector2D v) {
        try {
            if (v.x < 1e-9 || v.y < 1e-9) {
                throw new ArithmeticException("Division by zero in Vector2D");
            }
            this.x /= v.x;
            this.y /= v.y;
        } catch (ArithmeticException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void add(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public void subtract(double x, double y) {
        this.x -= x;
        this.y -= y;
    }

    public void multiply(double x, double y) {
        this.x *= x;
        this.y *= y;
    }

    public void divide(double x, double y) {
        try {
            if (x < 1e-9 || y < 1e-9) {
                throw new ArithmeticException("Division by zero in Vector2D");
            }
            this.x /= x;
            this.y /= y;
        } catch (ArithmeticException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public Vector2D plus(double x, double y) {
        return new Vector2D(this.x + x, this.y + y);
    }

    public Vector2D minus(double x, double y) {
        return new Vector2D(this.x - x, this.y - y);
    }

    public Vector2D times(double x, double y) {
        return new Vector2D(this.x * x, this.y * y);
    }

    public Vector2D dividedBy(double x, double y) {
        try {
            if (x < 1e-9 || y < 1e-9) {
                throw new ArithmeticException("Division by zero in Vector2D");
            }
        } catch (ArithmeticException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return new Vector2D(this.x / x, this.y / y);
    }

    public Vector2D plus(Vector2D v) {
        return new Vector2D(this.x + v.x, this.y + v.y);
    }

    public Vector2D minus(Vector2D v) {
        return new Vector2D(this.x - v.x, this.y - v.y);
    }

    public Vector2D times(Vector2D v) {
        return new Vector2D(this.x * v.x, this.y * v.y);
    }

    public Vector2D dividedBy(Vector2D v) {
        try {
            if (v.x < 1e-9 || v.y < 1e-9) {
                throw new ArithmeticException("Division by zero in Vector2D");
            }
        } catch (ArithmeticException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return new Vector2D(this.x / v.x, this.y / v.y);
    }

    public Vector2D scale(double sc) {
        return new Vector2D(this.x * sc, this.y * sc);
    }

    public Vector2D copy() {
        return new Vector2D(this.x, this.y);
    }

    public boolean equals(Vector2D v) { // nearly equals
        double epsilon = 1e-9;
        return Math.abs(this.x - v.x) < epsilon &&
               Math.abs(this.y - v.y) < epsilon;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
