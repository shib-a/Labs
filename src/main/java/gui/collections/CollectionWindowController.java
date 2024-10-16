package gui.collections;

import client.ClientMain;
import client.commands.RuntimeEnv;
import common.Feedbacker;
import common.Human;
import gui.AlertUtility;
import gui.visualization.VisualizationWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionWindowController {
    Logger logger = Logger.getLogger("cwc");
    @FXML
    private Button createButton;
    @FXML
    private Button clearButton;
    @FXML
    private Button visualizeButton;
    @FXML
    private Button commandsButton;
    @FXML
    private Button refreshButton;
    @FXML
    private Label filterByLabel;
    @FXML
    private Label catsLabel;
    @FXML
    private TextField filterByText;
    @FXML
    private TableView<Human> table;
    @FXML
    private TableColumn<Human,String> idColumn;
    @FXML
    private TableColumn<Human,String> nameColumn;
    @FXML
    private TableColumn<Human,String> statusColumn;
    @FXML
    private TableColumn<Human,String> colorColumn;
    @FXML
    private TableColumn<Human,String> isAliveColumn;
    @FXML
    private TableColumn<Human,String> statsColumn;
    @FXML
    private TableColumn<Human,String> ownerColumn;
    @FXML
    private TableColumn<Human,String> rarityColumn;
    @FXML
    private TableColumn<Human,String> coordXColumn;
    @FXML
    private TableColumn<Human,String> coordYColumn;
    public ResourceBundle currentBundle;
    @FXML
    private Button updateButton;
    @FXML
    private Button removeButton;
    @FXML
    private Button removeLowerButton;
    @FXML
    private Button countRarityButton;
    @FXML
    private Label comsLabel;
    @FXML
    private ToolBar commandsToolBar;

    @FXML
    private TextField updateField;
    @FXML
    private TextField removeField;
    @FXML
    private Label catNumberLabel;
    @FXML
    private Text usernameText;
    private ObservableList<Human> data;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private ComboBox<String> rarityBox;

    RuntimeEnv re = ClientMain.getRe();
    private final List<Locale> supportedLocales = Arrays.asList(
            new Locale("ru"),
            new Locale("is"),
            new Locale("da"),
            new Locale("es","GT")
    );
    private int currentLocaleIndex = 0;

    public void setLocale(int index) {
        this.currentLocaleIndex = index;
        currentBundle = ResourceBundle.getBundle("MessagesBundle", supportedLocales.get(currentLocaleIndex));
        updateUI();
    }

//    @FXML
//    protected void onGeoIconClick() {
//        currentLocaleIndex = (currentLocaleIndex + 1) % supportedLocales.size();
//        currentBundle = ResourceBundle.getBundle("MessagesBundle", supportedLocales.get(currentLocaleIndex));
//        updateUI();
//    }

    /**
     * Update LoginWindow UI
     */
    private void updateUI() {
        idColumn.setText(currentBundle.getString("id"));
        nameColumn.setText(currentBundle.getString("name"));
        coordXColumn.setText(currentBundle.getString("coordX"));
        coordYColumn.setText(currentBundle.getString("coordY"));
        statusColumn.setText(currentBundle.getString("status"));
        colorColumn.setText(currentBundle.getString("color"));
        isAliveColumn.setText(currentBundle.getString("isAlive"));
        statsColumn.setText(currentBundle.getString("stats"));
        ownerColumn.setText(currentBundle.getString("owner"));
        rarityColumn.setText(currentBundle.getString("rarity"));

        catsLabel.setText(currentBundle.getString("catsLabel"));
        filterByLabel.setText(currentBundle.getString("filterByLabel"));
        ObservableList<String> localizedItems = FXCollections.observableArrayList(
                currentBundle.getString("id"),
                currentBundle.getString("name"),
                currentBundle.getString("coordX"),
                currentBundle.getString("coordY"),
                currentBundle.getString("status"),
                currentBundle.getString("color"),
                currentBundle.getString("isAlive"),
                currentBundle.getString("stats"),
                currentBundle.getString("owner"),
                currentBundle.getString("rarity")
        );
        comboBox.getItems().setAll(localizedItems);
        commandsButton.setText(currentBundle.getString("commandsButton"));
        clearButton.setText(currentBundle.getString("clearButton"));
        refreshButton.setText(currentBundle.getString("refreshButton"));
        createButton.setText(currentBundle.getString("createButton"));
        visualizeButton.setText(currentBundle.getString("visualizeButton"));

        updateButton.setText(currentBundle.getString("updateButton"));
        removeLowerButton.setText(currentBundle.getString("removeLowerButton"));
        removeButton.setText(currentBundle.getString("removeButton"));
        countRarityButton.setText(currentBundle.getString("countRarityButton"));
        updateField.setPromptText(currentBundle.getString("id"));
        removeField.setPromptText(currentBundle.getString("id"));

    }

    @FXML
    private void initialize(){
        comboBox.getItems().addAll("id", "name", "status", "color", "isAlive", "stats", "owner", "rarity", "coord X", "coord Y");
        commandsToolBar.setVisible(false);

        data = FXCollections.observableArrayList();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
        isAliveColumn.setCellValueFactory(new PropertyValueFactory<>("isAlive"));
        statsColumn.setCellValueFactory(new PropertyValueFactory<>("stats"));
        ownerColumn.setCellValueFactory(new PropertyValueFactory<>("owner"));
        rarityColumn.setCellValueFactory(new PropertyValueFactory<>("rarity"));
        coordXColumn.setCellValueFactory(new PropertyValueFactory<>("coordX"));
        coordYColumn.setCellValueFactory(new PropertyValueFactory<>("coordY"));
        table.setItems(data);
        handle(re.executeCommand(new String[]{"show",""}).getMessage());
//        logger.info(re.getUser().getName());
        usernameText.setText(re.getUser().getName());
        rarityBox.getItems().add("THREE_STAR");
        rarityBox.getItems().add("FOUR_STAR");
        rarityBox.getItems().add("FIVE_STAR");
        idColumn.setPrefWidth(50);
        nameColumn.setPrefWidth(80);
        statusColumn.setPrefWidth(105);
        colorColumn.setPrefWidth(95);
        isAliveColumn.setPrefWidth(70);
        statsColumn.setPrefWidth(100);
        ownerColumn.setPrefWidth(90);
        rarityColumn.setPrefWidth(80);
        coordXColumn.setPrefWidth(80);
        coordYColumn.setPrefWidth(80);

//        loadOwnershipMap();
//        table.setRowFactory(tv -> new TableRow<Human>() {
//            @Override
//            public void updateItem(Human cat, boolean empty) {
//                super.updateItem(cat, empty);
//                if (cat == null) {
//                    setStyle("");
//                } else {
//                    Color color = clientColorMap.get(ownershipMap.get(cat.getId()));
//                    String rgb = String.format("#%02X%02X%02X",
//                            (int)(color.getRed() * 255),
//                            (int)(color.getGreen() * 255),
//                            (int)(color.getBlue() * 255));
//                    setStyle("-fx-border-color: " + rgb + ";");
//                }
//            }
//        });
//

        filterByText.setOnKeyPressed( event -> {
            if( event.getCode() == KeyCode.ENTER ) {
                logger.info("pressed");
                var arg = filterByText.getText();
                var how = comboBox.getSelectionModel().getSelectedItem();
//                how.stream().forEach(e->logger.info(e));
                if(how!=null) {
                    logger.info(arg);
                    filter(how, arg);
                }
            }
        } );
    }
    @FXML
    private void onCreateButtonClick(){
        logger.info("clicked");
        try {
            logger.info(re.getUser().getName());
            Feedbacker fb = re.executeCommand(new String[]{"add",""});
            logger.info(fb.getMessage());
            if(fb!=null) {
                try {
                    Human h = Human.fromCsvStr(fb.getMessage());
                    if (fb.getIsSuccessful()) {
                        data.add(h);
                        catNumberLabel.setText(String.valueOf(Integer.parseInt(catNumberLabel.getText())+1));
                    } else {
                        AlertUtility.infoAlert("Duplicate object pulled: "+ h.getName());
                        logger.info("already in col");}
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else logger.info("fb is null");
        } catch (Exception e) {
//            errorAlert("Server is dead :(");
        }
    }
    @FXML
    private void onRefreshButtonClick(){
        Feedbacker fb = re.executeCommand(new String[]{"show",""});
        var temp = data;
        data.removeAll(temp);
        handle(fb.getMessage());
    }
    @FXML
    protected void onCommandsButtonClick() {
        commandsToolBar.setVisible(!commandsToolBar.isVisible());

    }

    @FXML
    private void onVisualisationButtonClick(){
        VisualizationWindow visualizationWindow = new VisualizationWindow();
        visualizationWindow.show();
    }
    @FXML
    private void onClearButtonClick(){
        re.executeCommand(new String[]{"clear",""});
        Feedbacker fb = re.executeCommand(new String[]{"show",""});
        var temp = data;
        data.removeAll(temp);
        handle(fb.getMessage());
    }
    public void handle(String str){
        String[] strings = str.split("\n");
        int count = 0;
        for(String el : strings){
            Human h = Human.fromCsvStr(el);
            data.add(h);
            count++;
        }
        catNumberLabel.setText(String.valueOf(count));
    }

    @FXML
    protected void onGeoIconOneClick() {
        currentLocaleIndex = (currentLocaleIndex + 1) % supportedLocales.size();
        currentBundle = ResourceBundle.getBundle("MessagesBundle", supportedLocales.get(currentLocaleIndex));
        updateUI();
    }

    @FXML
    private void onUpdateButtonClick(){
//        RuntimeEnv re = ClientMain.getRe();
        String arg = updateField.getText();
        Feedbacker fb = re.executeCommand(new String[]{"update",arg});
        if (fb==null){

            return;
        }
        if (fb.getIsSuccessful()){
            Feedbacker fbc = re.executeCommand(new String[]{"show", ""});
            handle(fbc.getMessage());
        } else if (fb.getMessage().equals("No permission")){
            AlertUtility.errorAlert("Not enough rights for command execution");
        } else {
            AlertUtility.errorAlert("No element with such id");
        }
    }
    @FXML
    private void onRemoveButtonClick(){
        String arg = removeField.getText();
        Feedbacker fb = re.executeCommand(new String[]{"remove_by_id",arg});
        if (fb==null){
            return;
        }
        if (fb.getIsSuccessful()){
            Feedbacker fbc = re.executeCommand(new String[]{"show", ""});
            var temp = data;
            data.removeAll(temp);
            handle(fbc.getMessage());
        } else if (fb.getMessage().equals("No permission")){
            AlertUtility.errorAlert("Not enough rights for command execution");
        } else {
            AlertUtility.errorAlert("No element with such id");
        }
    }
    @FXML
    private void onRemoveLowerButtonClick(){
        Feedbacker fb = re.executeCommand(new String[]{"remove_lower","0"});
        if (fb==null){
            return;
        }
        if (fb.getIsSuccessful()){
            Feedbacker fbc = re.executeCommand(new String[]{"show", ""});
            var temp = data;
            data.removeAll(temp);
            handle(fbc.getMessage());
        } else if (fb.getMessage().equals("No permission")){
            AlertUtility.errorAlert("Not enough rights for command execution");
        } else {
            AlertUtility.errorAlert("No such element.");
        }
    }
    @FXML
    private void onCountRarityButtonClick(){
        String arg = rarityBox.getValue();
        Feedbacker fb = re.executeCommand(new String[]{"count_by_rarity",arg});
        if (fb==null){
            return;
        }
        if (fb.getIsSuccessful()){
            AlertUtility.infoAlert(fb.getMessage());
        } else if (fb.getMessage().equals("No permission")){
            AlertUtility.errorAlert("Not enough rights for command execution");
        } else {
            AlertUtility.errorAlert("No element with such rarity");
        }

    }
    public void filter(String how, String arg){
        Stream<Human> s = data.stream();
        String id = currentBundle.getString("id");
        String name = currentBundle.getString("name");
        String coordX = currentBundle.getString("coordX");
        String coordY = currentBundle.getString("coordY");
        String status = currentBundle.getString("status");
        String color = currentBundle.getString("color");
        String isAlive = currentBundle.getString("isAlive");
        String stats = currentBundle.getString("stats");
        String owner = currentBundle.getString("owner");
        String rarity = currentBundle.getString("rarity");
        logger.info(how);
        if(arg.isEmpty() || arg.isBlank() || arg==null){table.setItems(data);table.refresh();return;}
        if(how.equals(id)) {
            try {
                int parg = Integer.parseInt(arg);
                s = s.filter(el -> el.getId() == parg);
            } catch (NumberFormatException e) {
                AlertUtility.errorAlert("Wrong argument usage");
            }
        } else if(how.equals(name)){
            s = s.filter(el -> el.getName().equals(arg));
        } else if (how.equals(coordX)){
            try {
                double parg = Double.parseDouble(arg);
                s = s.filter(el -> el.getCoordX() == parg);
            }catch (NumberFormatException e){AlertUtility.errorAlert("Wrong argument usage");}
        }else if (how.equals(coordY)){
            try {
                double parg = Double.parseDouble(arg);
                s = s.filter(el -> el.getCoordY() == parg);
            }catch (NumberFormatException e){AlertUtility.errorAlert("Wrong argument usage");}
        }else if (how.equals(status)){
            s = s.filter(el -> el.getStatus().name().equals(arg));
        }else if(how.equals(color)){
            s = s.filter(el -> el.getColor().toString().equals(arg));
        }else if (how.equals(isAlive)){
            try{
                boolean parg = Boolean.parseBoolean(arg);
                s = s.filter(el -> el.getIsAlive()==parg);
            }catch (IllegalArgumentException e){AlertUtility.errorAlert("Wrong argument usage");}
        }else if (how.equals(stats)){
            s = s.filter(el -> el.getStats().equals(arg));
        }else if (how.equals(owner)){
            s=s.filter(el -> el.getOwner().equals(arg));
        }else if (how.equals(rarity)){
            s = s.filter(el -> el.getRarity().name().equals(arg));
        }
        table.setItems(FXCollections.observableArrayList(s.collect(Collectors.toList())));
        table.refresh();
    }
}
