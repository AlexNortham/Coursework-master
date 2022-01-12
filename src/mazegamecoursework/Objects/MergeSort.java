package mazegamecoursework.Objects;

public class MergeSort {



    public double[] sort(double[] list) { //This method will split the list passed to it in 2
        int length = list.length;
        if (length <= 1) {
            return list;
            //If the list length is 1 or less, the method will return the list as it is and will not attempt to split it

        }
        int middle = length / 2;
        double[] left = new double[middle];
        double[] right = new double[length - middle];
        //This creates 2 sub-lists that combined have the same length as the original list

        System.arraycopy(list, 0, left, 0, left.length);

        System.arraycopy(list, middle + 0, right, 0, right.length);
        //These add the contents of the old list into the 2 new lists

        double[] sorted = new double[length];
        left = sort(left);
        right = sort(right);
        //This calls the sort function on the 2 sub-lists

        sorted = merge(left, right);
        //This merges the list "left" and "right" into one in alphabetical order

        return sorted;
        //This returns the sorted list
    }

    private double[] merge(double[] left, double[] right) { //This method merges the 2 parametric lists into one list sorted from largest to smallest
        int leftLength = left.length;
        int rightLength = right.length;
        double[] merged = new double[leftLength + rightLength];
        //This creates a new list with a magnitude of that of both parametric lists combined

        int leftIndex = 0;
        int rightIndex = 0;
        int mergedIndex = 0;
        //These set up a series of pointers to use while navigating the lists

        while (leftIndex < leftLength || rightIndex < rightLength) { //This checks that at least one of the parametric lists has contents left to examine before executing subsequent code
            if (leftIndex < leftLength && rightIndex < rightLength) { //This checks whether both parametric lists have contents left to examine or not
                if (left[leftIndex] < right[rightIndex]) {
                    merged[mergedIndex] = right[rightIndex];
                    mergedIndex++;
                    rightIndex++;
                    //This checks whether the current item in the right list is larger than that in the left list. If so, the item in the right list is added to the new list and the next item is pointed towards in the right and new lists
                } else {
                    merged[mergedIndex] = left[leftIndex];
                    leftIndex++;
                    mergedIndex++;
                    //If the current item in the right list is not larger than that in the left list then the item in the left list is added to the new list and the next item is pointed towards in the left and new lists
                }
            } else if (leftIndex < leftLength) {
                merged[mergedIndex] = left[leftIndex];
                leftIndex++;
                mergedIndex++;
                //If only 1 parametric list has contents left to examine, if it is the left one, the item in the left list is added to the new list and the next item is pointed towards in the left and new lists

            } else if (rightIndex < rightLength) {
                merged[mergedIndex] = right[rightIndex];
                rightIndex++;
                mergedIndex++;
                //If only 1 parametric list has contents left to examine, if it is the right one, the item in the right list is added to the new list and the next item is pointed towards in the right and new lists

            }
        }

        return merged;
        //returns the merged list

    }

}
