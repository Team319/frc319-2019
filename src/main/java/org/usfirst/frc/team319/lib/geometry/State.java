package org.usfirst.frc.team319.lib.geometry;

import org.usfirst.frc.team319.lib.utils.CSVWritable;
import org.usfirst.frc.team319.lib.utils.Interpolable;

public interface State<S> extends Interpolable<S>, CSVWritable {
    double distance(final S other);

    @Override
    boolean equals(final Object other);

    @Override
    String toString();

    String toCSV();
}
