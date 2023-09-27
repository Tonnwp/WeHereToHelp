package ku.cs.directory.models;

import ku.cs.directory.services.Filterer;

import java.util.ArrayList;

public class ComplaintModelList {
    private ArrayList<ComplaintModel> complaints;

    public ComplaintModelList() {
        complaints = new ArrayList<>();
    }

    public void addComplain(ComplaintModel complaint) {
        complaints.add(complaint);
    }

    public ArrayList<ComplaintModel> getAllComplaints() {

        return complaints;
    }

    public ComplaintModelList filterBy(Filterer<ComplaintModel> filterer) {
        ComplaintModelList filtered = new ComplaintModelList();
        for (ComplaintModel key : getAllComplaints()) {
            if (filterer.filter((key))) {
                filtered.addComplain(key);
            }
        }
        return filtered;
    }

    public ComplaintModelList byCategory(String category) {
        return filterBy(new Filterer<ComplaintModel>() {
            @Override
            public boolean filter(ComplaintModel o) {
                return o.isCategory(category);
            }
        });
    }

    public ComplaintModel findByHeadline(String headline) {

        for (ComplaintModel complaint : complaints) {
            if (complaint.isHeadline(headline)) {
                return complaint;
            }
        }
        return null;
    }


}
