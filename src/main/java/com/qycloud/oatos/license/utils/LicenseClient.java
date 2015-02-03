package com.qycloud.oatos.license.utils;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFieldBuilder;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class LicenseClient extends Application implements EventHandler<Event>, ChangeListener<Number> {

	private static final SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd");

	private ComboBox<String> txtType;

	private TextField txtVersion;

	private TextField txtEnt;

	private TextField txtProdKey;

	private TextField txtUserKey;

	private TextField txtUser;

	private TextField txtDate;

	private TextField txtFolder;

	private Label msg;

	private Stage stage;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(final Stage stage) throws Exception {
		this.stage = stage;
		stage.setTitle("OATOS License");
		stage.setResizable(false);

		TabPane pane = new TabPane();
		pane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		
		stage.setScene(new Scene(pane, 450, 500));
		stage.show();
		
		Tab createTab = initCreateTab();
		Tab validateTab = initValidateTab();
		Tab prodKeyTab = initProdKeyTab();
		
		pane.getTabs().addAll(createTab, validateTab, prodKeyTab);
	}

	private Tab initCreateTab() {
		VBox box = new VBox();
		box.setAlignment(Pos.TOP_CENTER);
		box.setPadding(new Insets(30));
		box.setSpacing(15);

		VBox labelBox = new VBox();
		labelBox.setAlignment(Pos.TOP_RIGHT);
		labelBox.setSpacing(15);

		VBox inputBox = new VBox();
		inputBox.setAlignment(Pos.TOP_LEFT);
		inputBox.setSpacing(15);

		HBox hBox = new HBox();
		hBox.getChildren().addAll(labelBox, inputBox);
		box.getChildren().add(hBox);

		Label typeLabel = LabelBuilder.create().text("产品类型：").prefHeight(30).build();
		labelBox.getChildren().add(typeLabel);
		txtType = new ComboBox<String>();
		txtType.setPrefHeight(30);
		txtType.setPrefWidth(240);
		txtType.setPromptText("企业版/试用版");
		txtType.setItems(FXCollections.observableArrayList("企业版", "试用版"));
		txtType.setEditable(false);
		inputBox.getChildren().add(txtType);

		Label versionLabel = LabelBuilder.create().text("产品版本：").prefHeight(30).build();
		labelBox.getChildren().add(versionLabel);
		txtVersion = TextFieldBuilder.create().prefHeight(30).prefWidth(240).build();
		txtVersion.setPromptText("3.0");
		txtVersion.setText("3.0");
		inputBox.getChildren().add(txtVersion);

		Label entLabel = LabelBuilder.create().text("企业：").prefHeight(30).build();
		labelBox.getChildren().add(entLabel);
		txtEnt = TextFieldBuilder.create().prefHeight(30).prefWidth(240).build();
		txtEnt.setPromptText("企业云");
		inputBox.getChildren().add(txtEnt);

		Label prodKeyLabel = LabelBuilder.create().text("产品序列号：").prefHeight(30).build();
		labelBox.getChildren().add(prodKeyLabel);
		txtProdKey = TextFieldBuilder.create().prefHeight(30).prefWidth(240).build();
		txtProdKey.setText("62SU4-6I17X-H195X-9IERO-23YTX");
		txtProdKey.setPromptText("请输入序列号");
		inputBox.getChildren().add(txtProdKey);

		Label userKeyLabel = LabelBuilder.create().text("oatos.key：").prefHeight(30).build();
		labelBox.getChildren().add(userKeyLabel);
		txtUserKey = TextFieldBuilder.create().prefHeight(30).prefWidth(240).build();
		txtUserKey.setText("");
		txtUserKey.setPromptText("请输入oatos.key文件中的内容");
		inputBox.getChildren().add(txtUserKey);

		Label userLabel = LabelBuilder.create().text("用户数：").prefHeight(30).build();
		labelBox.getChildren().add(userLabel);
		txtUser = TextFieldBuilder.create().prefHeight(30).prefWidth(240).build();
		txtUser.setText("300");
		txtUser.setPromptText("请输入用户数");
		inputBox.getChildren().add(txtUser);

		Label dateLabel = LabelBuilder.create().text("到期日期：").prefHeight(30).build();
		labelBox.getChildren().add(dateLabel);
		txtDate = TextFieldBuilder.create().prefHeight(30).prefWidth(240).build();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 15);
		txtDate.setPromptText(sdf.format(cal.getTime()));
		txtDate.setText(sdf.format(cal.getTime()));
		inputBox.getChildren().add(txtDate);

		Label folderLabel = LabelBuilder.create().text("选择文件夹：").prefHeight(30).build();
		labelBox.getChildren().add(folderLabel);
		txtFolder = TextFieldBuilder.create().prefHeight(30).prefWidth(240).build();
		txtFolder.setPromptText("...");
		inputBox.getChildren().add(txtFolder);

		txtFolder.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				msg.setText("");
				DirectoryChooser chooser = new DirectoryChooser();
				File folder = chooser.showDialog(stage);
				if (folder != null) {
					txtFolder.setText(folder.getPath());
				}
			}
		});

		txtType.getSelectionModel().selectedIndexProperty().addListener(this);
		txtVersion.setOnKeyPressed(this);
		txtEnt.setOnKeyPressed(this);
		txtProdKey.setOnKeyPressed(this);
		txtDate.setOnKeyPressed(this);
	
		msg = LabelBuilder.create().prefHeight(30).build();
		box.getChildren().add(msg);

		final Button btnOk = ButtonBuilder.create().prefWidth(60)
				.prefHeight(40).text("生成").build();
		box.getChildren().add(btnOk);
		btnOk.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				btnOk.setDisable(true);
				try {
					msg.setText("");
					License license = getCreateInput();
					if (license != null) {
						File file = new File(txtFolder.getText(), "oatos.lic");
						msg.setText("生成中...");
						LicenseUtil.get().toXml(license, file);
						msg.setText("生成成功：" + file.getPath());
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					msg.setText("生成失败！");
				} finally {
					btnOk.setDisable(false);
				}
			}
		});
		
		Tab createTab = new Tab("生成");
		createTab.setContent(box);
		return createTab;
	}

	private License getCreateInput() throws ParseException {
		License license = new License();

		String type = txtType.getSelectionModel().getSelectedItem();
		if (type != null) {
			license.setLicenseType(type);
		} else {
			msg.setText("请选择产品类型");
			return null;
		}

		String version = txtVersion.getText();
		if (version != null && !"".equals(version.trim())) {
			license.setProductVersion(version.trim());
		} else {
			msg.setText("请输入产品版本");
			return null;
		}

		String ent = txtEnt.getText();
		if (ent != null && !"".equals(ent.trim())) {
			license.setLicenseTo(ent.trim());
		} else {
			msg.setText("请输入企业");
			return null;
		}

		String key = txtProdKey.getText();
		if (key != null && !"".equals(key.trim())) {
			license.setKey(key.trim().toUpperCase());
		} else {
			msg.setText("请输入序列号");
			return null;
		}

		license.setKey2(txtUserKey.getText().trim());

		try {
			String user = txtUser.getText();
			license.setMaxUser(Integer.parseInt(user.trim()));
		} catch (Exception e) {
			msg.setText("请输入用户数");
			return null;
		}

		try {
			if (txtDate.getText().contains("-")) {
				sdf.parse(txtDate.getText().trim());
			} else {
				Integer.parseInt(txtDate.getText().trim());
			}
			license.setExpiry(txtDate.getText().trim());
		} catch (Exception e) {
			msg.setText("到期日期");
			return null;
		}

		String folder = txtFolder.getText();
		if (folder == null || "".equals(folder.trim())) {
			msg.setText("请选择文件夹");
			return null;
		}

		return license;
	}

	private Tab initValidateTab() {
		VBox box = new VBox();
		box.setAlignment(Pos.CENTER);
		box.setPadding(new Insets(30));
		box.setSpacing(15);

		final Button btnValidate = ButtonBuilder.create().prefWidth(120)
				.prefHeight(40).text("选择license文件").build();
		box.getChildren().add(btnValidate);
		
		final Label msg = LabelBuilder.create().prefHeight(30).build();
		box.getChildren().add(msg);

		btnValidate.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				msg.setText("");
				btnValidate.setDisable(true);
				try {
					FileChooser chooser = new FileChooser();
					File file = chooser.showOpenDialog(stage);
					if (file != null) {
						License license = LicenseUtil.get().fromXML(file);
						if (license != null) {
							txtType.getSelectionModel().select(license.getLicenseType());
							txtVersion.setText(license.getProductVersion());
							txtEnt.setText(license.getLicenseTo());
							txtProdKey.setText(license.getKey());
							txtUserKey.setText(license.getKey2());
							txtUser.setText(license.getMaxUser() + "");
							txtDate.setText(license.getExpiry());
							msg.setText("验证成功，请在生成tab中查看");
						} else {
							msg.setText("验证失败!");
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					msg.setText("验证失败!");
				} finally {
					btnValidate.setDisable(false);
				}

			}
		});

		Tab validateTab = new Tab("验证");
		validateTab.setContent(box);
		return validateTab;
	}

	private Tab initProdKeyTab() {
		VBox box = new VBox();
		box.setAlignment(Pos.CENTER);
		box.setPadding(new Insets(30));
		box.setSpacing(15);

		final Button btnCreate = ButtonBuilder.create().prefWidth(120)
				.prefHeight(40).text("生成序列号").build();
		box.getChildren().add(btnCreate);
	
		final TextField txtKey = TextFieldBuilder.create().prefHeight(30).prefWidth(240).build();
		box.getChildren().add(txtKey);
		
		final Label msg = LabelBuilder.create().prefHeight(30).build();
		box.getChildren().add(msg);

		btnCreate.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				txtKey.setText("");
				msg.setText("");
				btnCreate.setDisable(true);
				try {
					StringBuffer key = new StringBuffer();
					key.append(Security.randomCharString(5)).append("-");
					key.append(Security.randomCharString(5)).append("-");
					key.append(Security.randomCharString(5)).append("-");
					key.append(Security.randomCharString(5)).append("-");
					key.append(Security.randomCharString(5));
					txtKey.setText(key.toString().toUpperCase());
					txtProdKey.setText(key.toString().toUpperCase());
				} catch (Exception ex) {
					ex.printStackTrace();
					msg.setText("生成失败!");
				} finally {
					btnCreate.setDisable(false);
				}

			}
		});

		Tab validateTab = new Tab("序列号");
		validateTab.setContent(box);
		return validateTab;
	}

	@Override
	public void changed(ObservableValue<? extends Number> arg0, Number arg1,
			Number arg2) {
		msg.setText("");
	}

	@Override
	public void handle(Event arg0) {
		msg.setText("");
	}

}
