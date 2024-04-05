package com.alec.spring.rest;

import com.alec.spring.rest.configuration.myConfig;
import com.alec.spring.rest.entity.Chat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@XmlRootElement
public class Initialization {
    static int counter=0;
    static List<Chat> outputArr = new ArrayList<>() ;
    static AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(myConfig.class);

    static Communication communication = context.getBean("communication", Communication.class);
    static File file = new File("chatInit.xml");
    // static List<Initialization> outputArr = new ArrayList<>();
    static Map<String, String> help = new HashMap<>();

    private List<Initialization> listInit = new ArrayList<Initialization>();

    public Initialization(String name, String message, Date data) {
        this.name = name;
        this.message = message;
        this.data = data;
    }

    @XmlElement
    public List<Initialization> getListInit() {
        return listInit;
    }

    public void setListInit(List<Initialization> listInit) {
        this.listInit = listInit;
    }

    String name;

    String message;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.

            class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    //  @CreationTimestamp
    @XmlElement
    Date data;
    public Initialization() {
    }

    public Initialization(String name, String message) {
        this.name = name;
        this.message = message;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getData() {
        return data;
    }

    public void Data(Date data) {
        this.data = data;
    }

    public void Init() {
        System.out.println("Добро пожаловать в чат. Введите имя пользователя: ");
        List<Initialization> listInit = new ArrayList<>();
        List<Chat> outputArr = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        InitXmlList("CHAT-INFO", "Пользователь " + name + " вошёл в чат", listInit);
        System.out.println("Отлично. Теперь можно вводить сообщения");
        System.out.println("Прим. - введите команду help для вызова справочного меню");


        sendMessage(name, listInit, new Date());
    }

    public static void sendMessage(String name, List<Initialization> listInit, Date date) {

        Scanner scanner = new Scanner(System.in);


        String message = scanner.nextLine();
        if (message.equals("help")) {
            Initialization.Help(name, listInit);


        }
        if (message.equals("exit")) {
            InitXmlList("CHAT-INFO", "Пользователь " + name + " покинул чат", listInit);
            System.out.println("Вы покинули чат");
        }

        if (message.equals("showAll")) {
            List<Chat> allMessages = communication.showAllMessages();
            for (Chat i : allMessages) {
                System.out.println("============BACK CHAT================");
                System.out.println(i.getUserName());
                System.out.println("(" + i.getData() + "): ");
                System.out.println(i.getMessage());
            }

            sendMessage(name, listInit, new Date());

        } else if (!message.equals("exit") && !message.equals("help") && !message.equals("showAll") && !(
                name.equals("CHAT-INFO"))) {
            InitXmlList(name, message, listInit);
            sendMessage(name, listInit, new Date());
        }
    }

    public static void InitXmlList(String name, String message, List<Initialization>
            listInit) {
        Date date = new Date();
        try {
            Initialization chat = new Initialization(name, message, date);
            listInit.add(chat);
            Initialization initialization = new Initialization();
            initialization.setListInit(listInit);

            JAXBContext jaxbContext = JAXBContext.newInstance(Initialization.class);


            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
          //  marshaller.marshal(initialization, System.out);
            marshaller.marshal(initialization, file);
            SendMessageToDataBase(name, message,
                    listInit, date);
        } catch (JAXBException | InterruptedException e) {
            e.printStackTrace();
        }
        return;
//sendMessage(name,listInit);}
    }

    public static void Help(String name, List<Initialization> listInit) {
        System.out.println("Справочное меню:");

        help.put("showAll", "Открыть все сообщения");

        help.put("exit", "Покинуть чат");

        for (Map.Entry<String, String> entry : help.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
        System.out.println("Отлично. Продолжаем");
        sendMessage(name, listInit, new Date());
    }

    public static void SendMessageToDataBase(String name, String message, List<Initialization>
            listInit, Date data) throws InterruptedException {
if (counter==4){outputArr.clear();}

        Runnable myThread = null;
        Thread thread1 = new Thread(myThread);

        try {


            JAXBContext jc = JAXBContext.newInstance(Initialization.class);
            Unmarshaller ums = jc.createUnmarshaller();
            Initialization li = (Initialization) ums.unmarshal(new File("chatInit.xml"));
            Chat chat = new Chat();
            for (Initialization i : li.getListInit()) {
                String name1 = i.getName();
                String message1 = i.getMessage();

                Date date1 = i.getData();

                chat = new Chat(message1, name1, date1);


            }
            communication.saveChat(chat);
            outputArr.add(chat);
            counter++;
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (counter == 1) {

            communication.showLastMessages();
counter=counter-1;
            }

;
        }


    @Override
    public String toString() {
        return "Initialization{" +
                "name='" + name + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}

