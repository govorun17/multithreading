package org.example.waitnotify;

import org.example.thread.base.ColorConst;

public class WaitNotify {
    public static void main(String[] args) {

        Message msg = new Message();

        new Thread(new Producer(msg)).start();
        new Thread(new Consumer(msg)).start();
    }

    private static class Producer implements Runnable {

        private final Message message;

        Producer(Message m) {
            this.message = m;
        }

        String[] text = {
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
            for (String m : text) {
                synchronized (message) {
                    System.out.println(ColorConst.GREEN + "Producing message: " + m);
                    message.setMessage(m);
                    message.notify();
                    if (!m.equals("DONE")) {
                        message.wait();
                    }
                }
                Thread.sleep(400);
            }
        }
    }

    private static class Consumer implements Runnable {

        private final Message message;

        Consumer(Message m) {
            this.message = m;
        }

        @Override
        public void run() {
            try {
                consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void consume() throws InterruptedException {
            while (true) {
                Thread.sleep(400);
                synchronized (message) {
                    System.out.println(ColorConst.RED + "Consuming message: " + message.getMessage());
                    if (!message.getMessage().equals("DONE")) {
                        message.notify();
                        message.wait();
                    } else {
                        return;
                    }
                }
            }
        }
    }
}
