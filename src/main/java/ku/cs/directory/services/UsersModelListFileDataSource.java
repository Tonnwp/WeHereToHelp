package ku.cs.directory.services;

import ku.cs.directory.models.AdminModel;
import ku.cs.directory.models.OfficerModel;
import ku.cs.directory.models.UserModel;
import ku.cs.directory.models.UserModelList;

import java.io.*;

public class UsersModelListFileDataSource implements DataSource<UserModelList> {
    private String directoryName;
    private String fileName;

    public UsersModelListFileDataSource(String directoryName, String fileName) {
        this.directoryName = directoryName;
        this.fileName = fileName;
        checkFileIsExisted();
    }

    private void checkFileIsExisted() {
        File file = new File(directoryName);
        if (!file.exists()) {
            file.mkdirs();
        }

        String filePath = directoryName + File.separator + fileName;
        file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public UserModelList readData() {
        UserModelList userModelList = new UserModelList();
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader reader = null;
        BufferedReader buffer = null;

        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);
            String line = "";
            while ((line = buffer.readLine()) != null) {
                String[] data = line.split(",");
                String role = data[0];
                UserModel user = null;
                if (role.equals("user")) {
                    user = new UserModel(
                            data[1].trim(),
                            data[2].trim(),
                            data[3].trim(),
                            data[4].trim()
                    );
                    user.setDate(data[5]);
                } else if (role.equals("officer")) {
                    user = new OfficerModel(
                            data[1].trim(),
                            data[2].trim(),
                            data[3].trim(),
                            data[4].trim(),
                            data[6].trim()
                    );
                    user.setDate(data[5]);
                } else if (role.equals("admin")) {
                    user = new AdminModel(
                            data[1].trim(),
                            data[2].trim(),
                            data[3].trim(),
                            data[4].trim()
                    );
                    user.setDate(data[5]);
                }
                userModelList.addUsers(user);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                buffer.close();
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return userModelList;
    }

    @Override
    public void writeData(UserModelList userModelList) {
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileWriter fileWriter = null;
        BufferedWriter writer = null;

        try {
            fileWriter = new FileWriter(file);
            writer = new BufferedWriter(fileWriter);

            for (UserModel user : userModelList.getAllUsers()) {
                String line = user.toCSV();
                writer.append(line);
                writer.newLine();

            }
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
