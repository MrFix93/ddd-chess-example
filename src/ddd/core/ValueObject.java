package ddd.core;

import java.util.ArrayList;
import java.util.Vector;

public abstract class ValueObject {
    protected abstract Object[] GetAtomicValues();

    public boolean equals(Object obj) {
        return  obj != null &&
                this.getClass().equals(obj.getClass()) &&
                AreEqual(this, (ValueObject) obj);
    }

    public int hashCode() {
        int result = 0;
        for (Object value : GetAtomicValues()) {
            if (value != null){
                result = result ^ value.hashCode();
            }
        }
        return result;
    }

    private static boolean AreEqual(ValueObject left, ValueObject right) {
        if ((Object) left == null)
            return (Object) right == null;
        else if ((Object) right == null)
            return false;
        else {
            Object[] leftValues = left.GetAtomicValues();
            Object[] rightValues = right.GetAtomicValues();

            boolean result = true;
            for (int i = 0; i < leftValues.length; i++) {
                result = result && leftValues[i].equals(rightValues[i]);
            }
            return result;
        }
    }
}
