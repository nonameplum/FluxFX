package todoflux;

import eu.lestard.easydi.EasyDI;
import eu.lestard.fluxfx.Dispatcher;
import eu.lestard.fluxfx.ViewLoader;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import todoflux.stores.ItemsStore;
import todoflux.views.MainView;


public class App extends Application {

    public static void main(String... args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        EasyDI context = new EasyDI();

        context.bindInstance(Dispatcher.class, Dispatcher.getInstance());
        context.markAsSingleton(Dispatcher.class);

        final Dispatcher dispatcher = context.getInstance(Dispatcher.class);

        final ItemsStore itemStore = context.getInstance(ItemsStore.class);
        dispatcher.register(itemStore);

        ViewLoader.setDependencyInjector(context::getInstance);

        final Parent parent = ViewLoader.load(MainView.class);

        stage.setScene(new Scene(parent));
        stage.show();
    }
}
