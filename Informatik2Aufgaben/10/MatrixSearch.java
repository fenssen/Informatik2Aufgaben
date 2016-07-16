public class MatrixSearch
{
    /**
     * Returns <code>true</code> if the array is a matrix, false otherwise.
     *
     * @param a The assumed matrix
     * @return <code>True</code> if a is matrix, false otherwise
     */
    private static boolean isMatrix(int[][] a)
    {
        if (a == null)
            throw new NullPointerException("Argument 'a' must not be null!");
        for (int cnt = 0; cnt < a.length; cnt++)
            if (a[cnt] == null)
                throw new NullPointerException("Argument 'a' must not be null!");
        int n = a[0].length;
        for (int cnt = 1; cnt < a.length; cnt++)
            if (a[cnt].length != n)
                return false;
        return true;
    }

    /**
     * Returns <code>true</code> if the matrix contains x, false otherwise.
     *
     * @param array The given array
     * @param x The number to search for
     * @return <code>True</code> if the matrix contains x, false otherwise
     * @throws IllegalArgumentException If array is not a matrix
     */
    public boolean arrayContainsElem(int[][] array, int x)
    {
        // Checks if the given arguments are valid
        if (array == null)
            throw new NullPointerException("Argument 'array' must not be null!");
        for (int cnt = 0; cnt < array.length; cnt++)
            if (array[cnt] == null)
                throw new NullPointerException("Argument 'array' must not be null!");
        if (!MatrixSearch.isMatrix(array))
            throw new IllegalArgumentException("The given array has to be a matrix!");

        // Search on x
        int i = array.length-1,
        j = 0;
        while (i >= 0 && j < array[0].length)
        {
            //Position1
            if (array[i][j] == x)
            //Position2
                return true;

            if (array[i][j] > x)
                i--;
            else
                j++;
        }
        //Position3
        return false;
    }

    // Tests MatrixSearch#arrayContainsElem
    public static void main(String[] args)
    {
        int[][] matrix = {
                { 2, 5, 11, 21, 28, 32, 42 },
                { 3, 7, 15, 22, 30, 37, 49 },
                { 8, 10, 23, 40, 43, 47, 52 },
                { 14, 19, 33, 41, 46, 54, 63 },
                { 29, 31, 34, 45, 51, 58, 67 },
            };
        MatrixSearch m = new MatrixSearch();
        

        //Tests
        System.out.println("Ist die Zahl 42 im Array enthalten:" + m.arrayContainsElem(matrix, 42));
        System.out.println("Ist die Zahl 99 im Array enthalten:" + m.arrayContainsElem(matrix, 99));
    }
}
