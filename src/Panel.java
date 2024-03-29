import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.function.IntConsumer;

public class Panel extends JPanel {

    JComboBox<Object> jComboBox2 = new JComboBox<>();
    JButton SortButton1 = new JButton();
    JButton ResetButton = new JButton();
    Integer[] array = {6, 1, 3, 5, 2, 4};


    Panel() {
        setPreferredSize(new Dimension(2400, 800));

        setBackground(new java.awt.Color(51, 153, 255));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Bubble sort", "Selection sort", "Insertion sort", "Merge sort", "Quick sort", "Heap sort"}));
        jComboBox2.addActionListener(this::jComboBox2ActionPerformed);
        SortButton1.setText("Sort");
        ResetButton.setText("Reset");
        SortButton1.addActionListener(new java.awt.event.ActionListener() {
                                          public void actionPerformed(java.awt.event.ActionEvent evt) {
                                              SortButton1ActionPerformed(evt);
                                          }
                                      }
        );
        this.add(jComboBox2);
        this.add(SortButton1);
        ResetButton.addActionListener(this::resetArray);
        this.add(ResetButton);
    }

    private void resetArray(ActionEvent evt) {
        var list = Arrays.asList(1, 2, 3, 4, 5, 6);
        Collections.shuffle(list);
        array = list.toArray(new Integer[0]);
        repaint();
    }


    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void SortButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        String selected = jComboBox2.getSelectedItem().toString();
        switch (selected) {
            case "Bubble sort":
                animate(i -> bubbleSort(array, i), 200);
                break;
            case "Selection sort":
                animate(i -> selectionSort(array, i), 200);
                break;
            case "Insertion sort":
                animate(i -> insertionSort(array, i), 200);
                break;
            case "Merge sort":
                new Thread(() -> mergeSort(array, 0, array.length - 1)).start();
                break;
            case "Quick sort":
                new Thread(() -> quickSort(array, 0, array.length - 1)).start();
                break;
            case "Heap sort":
                new Thread(() -> heapSort(array)).start();
                break;
        }
    }

    private void animate(IntConsumer consume, int time) {
        new Thread(() -> {
            for (int i = 0; i < array.length; i++) {
                consume.accept(i);
                render(time);
            }
        }).start();
    }

    public void render(int time) {
        repaint();
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.BLUE);
        for (int i = 0; i < array.length; i++) {
            int imageSize = array[i] * 50;

            // Load an image
            ImageIcon icon = new ImageIcon("C:\\Users\\nnn\\IdeaProjects\\SortProject\\src\\resourses\\Nemo.png");
            Image image = icon.getImage();

            // Draw the image with calculated size
            g.drawImage(image, i * 315 + 20, getHeight() - imageSize - 50, imageSize, imageSize, this);
        }
    }

    public void bubbleSort(Integer[] array, int target) {
        int i = target;
        for (int j = i + 1; j < array.length; j++) {
            if (array[i] > array[j]) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                repaint();
            }
        }
    }

    public void selectionSort(Integer[] array, int target) {
        int min = target;
        for (int i = target + 1; i < array.length; i++) {
            if (array[min] > array[i])
                min = i;
        }
        if (min != target) {
            int temp = array[target];
            array[target] = array[min];
            array[min] = temp;
        }
    }

    void insertionSort(Integer[] array, int index) {

        int key = array[index];
        int j = index - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
        while (j >= 0 && array[j] > key) {
            array[j + 1] = array[j];
            j = j - 1;
        }
        array[j + 1] = key;
    }

    void sort(Integer[] array, int low, int mid, int high) {
        Integer[] temp = Arrays.copyOf(array, array.length);
        int i = low, j = mid + 1, k = low;
        while (i <= mid && j <= high) {
            if (temp[i] <= temp[j]) {
                array[k++] = temp[i++];
            } else {
                array[k++] = temp[j++];
            }
        }
        while (i <= mid) {
            array[k++] = temp[i++];
        }
        while (j <= high) {
            array[k++] = temp[j++];
        }
    }

    void mergeSort(Integer[] array, int l, int h) {
        if (l < h) {

            int m = l + (h - l) / 2;

            mergeSort(array, l, m);
            mergeSort(array, m + 1, h);

            sort(array, l, m, h);
            repaint();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    void swap(Integer[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    int partition(Integer[] arr, int low, int high) {
        int pivot = arr[high];

        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {

            if (arr[j] < pivot) {

                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    void quickSort(Integer[] arr, int low, int high) {
        if (low < high) {


            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);

            repaint();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void heapSort(Integer[] arr) {
        int N = arr.length;

        for (int i = N / 2 - 1; i >= 0; i--)
            heapify(arr, N, i);

        for (int i = N - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);

            repaint();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    void heapify(Integer[] arr, int N, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < N && arr[l] > arr[largest])
            largest = l;

        if (r < N && arr[r] > arr[largest])
            largest = r;

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            heapify(arr, N, largest);
        }
    }

}

