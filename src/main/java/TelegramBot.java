import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.util.List;

public class TelegramBot extends TelegramLongPollingBot {
    private static String botName;
    private static String token;

    public static void main(String[] args) throws TelegramApiRequestException {
        botName = args[0];
        token = args[1];
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        telegramBotsApi.registerBot(new TelegramBot());


    }

    public void onUpdateReceived(Update update) {
        List<String> scrapeResults = ScrapeItAll.scrape();
        for (String event : scrapeResults) {
            SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId())
                    .setText(event);
            try {
                execute(sendMessage); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public String getBotUsername() {
        return botName;
    }

    public String getBotToken() {
        return token;
    }
}
