package ku.cs.directory.services;

import ku.cs.directory.models.ComplaintModel;
import ku.cs.directory.models.ComplaintModelList;

import java.io.*;

public class ComplainModelListDataSource implements DataSource<ComplaintModelList> {
    private String directoryName;
    private String fileName;

    public ComplainModelListDataSource(String directoryName, String fileName) {
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
    public ComplaintModelList readData() {
        ComplaintModelList complaintModelList = new ComplaintModelList();
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
                ComplaintModel complaint = new ComplaintModel(
                        data[0].trim(),
                        data[1].trim(),
                        data[2].trim(),
                        data[3].trim(),
                        data[4].trim(),
                        Integer.parseInt(data[5].trim()),
                        data[6].trim(),
                        data[7].trim(),
                        data[8].trim(),
                        data[9].trim()
                );
                complaintModelList.addComplain(complaint);
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
        return complaintModelList;

    }

    @Override
    public void writeData(ComplaintModelList complaintModelList) {
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileWriter fileWriter = null;
        BufferedWriter writer = null;
        try {
            fileWriter = new FileWriter(file);
            writer = new BufferedWriter(fileWriter);

            for (ComplaintModel complaint : complaintModelList.getAllComplaints()) {
                String line = complaint.toCSV();
                writer.append(line);
                writer.newLine();

            }
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
