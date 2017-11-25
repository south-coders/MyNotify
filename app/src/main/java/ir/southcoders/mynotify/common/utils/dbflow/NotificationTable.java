package ir.southcoders.mynotify.common.utils.dbflow;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.UUID;

/**
 * Created by Farzad on 11/25/2017.
 */

public class notific extends BaseModel{
    @Table(database = AppDatabase.class)
    public class User {

        @PrimaryKey // at least one primary key required
        UUID id;

        @Column
        String name;

        @Column
        int age;
    }
}
