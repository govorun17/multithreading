package org.example.producerconsumer;

import org.example.thread.base.ColorConst;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProdCons {

    private static BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);

    public static void main(String[] args) {
        new Thread(new Producer()).start();
        new Thread(new Consumer()).start();



    }

    private static class Producer implements Runnable {

        String[] msg = {
            "В целом, конечно, повышение уровня гражданского сознания способствует подготовке и реализации новых предложений.",
            "Таким образом, сложившаяся структура организации в значительной степени обусловливает важность инновационных методов управления процессами.",
            "Учитывая ключевые сценарии поведения, глубокий уровень погружения в значительной степени обусловливает важность поэтапного и последовательного развития общества.",
            "И нет сомнений, что некоторые особенности внутренней политики, которые представляют собой яркий пример континентально-европейского типа политической культуры, будут функционально разнесены на независимые элементы.",
            "Кстати, действия представителей оппозиции представлены в исключительно положительном свете.",
            "В своём стремлении улучшить пользовательский опыт мы упускаем, что сделанные на базе интернет-аналитики выводы рассмотрены исключительно в разрезе маркетинговых и финансовых предпосылок!",
            "Противоположная точка зрения подразумевает, что сторонники тоталитаризма в науке формируют глобальную экономическую сеть и при этом — ограничены исключительно образом мышления.",
            "Ясность нашей позиции очевидна: консультация с широким активом способствует подготовке и реализации экономической целесообразности принимаемых решений.",
            "Как уже неоднократно упомянуто, сделанные на базе интернет-аналитики выводы заблокированы в рамках своих собственных рациональных ограничений.",
            "Господа, современная методология разработки влечет за собой процесс внедрения и модернизации экспериментов, поражающих по своей масштабности и грандиозности.",
            "С другой стороны, реализация намеченных плановых заданий создаёт необходимость включения в производственный план целого ряда внеочередных мероприятий с учётом комплекса существующих финансовых и административных условий.",
            "Господа, разбавленное изрядной долей эмпатии, рациональное мышление позволяет выполнить важные задания по разработке форм воздействия.",
            "DONE"
        };

        @Override
        public void run() {
            try {
                produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void produce() throws InterruptedException {
            Random r = new Random();
            for (int i = 0; i < msg.length; i++) {
                queue.put(msg[i]);
                System.out.println(ColorConst.GREEN + "Producing " + msg[i] + " Queue size: " + queue.size());
                Thread.sleep(r.nextInt(1000));
            }
        }
    }

    private static class Consumer implements Runnable {

        @Override
        public void run() {
            try {
                consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void consume() throws InterruptedException {
            Random r = new Random();
            while (true) {
                String msg = queue.take();
                System.out.println(ColorConst.RED + "Consuming " + msg + " Queue size: " + queue.size());
                if (msg.equals("DONE")) {
                    return;
                } else {
                    Thread.sleep(r.nextInt(10000));
                }
            }
        }
    }
}
