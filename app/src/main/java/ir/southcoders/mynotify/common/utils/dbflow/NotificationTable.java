package ir.southcoders.mynotify.common.utils.dbflow;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.Random;
import java.util.UUID;

/**
 * Created by Farzad on 11/25/2017.
 */
@Table(database = AppDatabase.class)
public class NotificationTable extends BaseModel{
    @Column
    @PrimaryKey
    int id = (new Random()).nextInt(9999999);

    @Column
    String Package = null;

    @Column
    String Ticker = null;

    @Column
    String Title = null;

    @Column
    String Date = null;

    @Column
    String ImagePath = null;

    @Column
    String Text = null;

    public int getId() {
        return id;
    }

    public String getPackage() {
        return Package;
    }

    public void setPackage(String aPackage) {
        this.Package = aPackage;
    }

    public String getTicker() {
        return Ticker;
    }

    public void setTicker(String ticker) {
        Ticker = ticker;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }
}
