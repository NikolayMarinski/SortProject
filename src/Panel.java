import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.function.IntConsumer;

public class Panel extends JPanel {
    JComboBox<Object> jComboBox2 = new JComboBox<>();
    Integer[] array = {6, 1, 3, 5, 2, 4};


    Panel() {
        setPreferredSize(new Dimension(1500, 800));

        JButton SortButton1 = new JButton();
        JButton ResetButton = new JButton();


        setBackground(new java.awt.Color(51, 153, 255));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Bubble sort", "Selection sort", "Insertion sort", "Merge sort", "Quick sort", "Heap sort"}));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

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

        add(ResetButton);
    }

    private void resetArray(ActionEvent evt) {
//        List<java.lang.Integer> list = (List<Integer>) Arrays.asList(array);
//        Collections.shuffle(list);
//        array = list.toArray(new Integer[0]);
//        repaint();
    }


    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void SortButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        String selected = jComboBox2.getSelectedItem().toString();
        switch (selected) {
            case "Bubble sort":
                animate(i->bubbleSort(array, i), 200);
                break;
            case "Selection sort":
                animate(i->selectionSort(array, i), 200);
            case "Insertion sort":
                animate(i->insertionSort(array, i), 200);
            case "Merge sort":
                //Merge sort
            case "Quick sort":
                //Quick sort
            case "Heap sort":
                //Heap sort
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
            int imageSize = array[i] * 50; // Each element multiplied by 50

            // Load an image
            ImageIcon icon = new ImageIcon("C:\\Users\\nnn\\IdeaProjects\\SortProject\\src\\resourses\\mustang.jpg");
            Image image = icon.getImage();

            // Draw the image with calculated size
            g.drawImage(image, imageSize + i * 200, getHeight() - imageSize - 50, imageSize, imageSize, this);
        }
    }

    public void bubbleSort(int[] array, int target) {
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

    public void selectionSort(int[] array, int target) {
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

    void insertionSort(int[] array, int index) {

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

}

