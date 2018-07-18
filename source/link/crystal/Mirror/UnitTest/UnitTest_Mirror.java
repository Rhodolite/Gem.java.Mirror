//  Copyright (c) 2018 Joy Diamond.  All rights reserved.


package link.crystal.Mirror.UnitTest;


import link.crystal.Gem.Core.Gem_Object;
import link.crystal.Gem.Core.Gem_StringBuilder;
import link.crystal.Gem.Core.Zone;
import link.crystal.Gem.Inspection.Inspection;
import link.crystal.Gem.Interface.Inspectable;
import link.crystal.Mock.Shape;


public final class  UnitTest_Mirror
    extends         Gem_Object <Inspection>
//  extends         Object
    implements      Inspectable<Inspection>//,
{
    private static final Inspection     inspection = Inspection.create("UnitTest_Mirror");


    //
    //  Members
    //
    public final Zone                   z;


    //
    //  Constructor & Factory
    //
    private                             UnitTest_Mirror(final Zone z)
    {
        this.z = z;
    }


    public static final UnitTest_Mirror     create(final Zone z)
    {
        return new UnitTest_Mirror(z);
    }


    //
    //  Interface Inspectable
    //
    @Override
    public final Inspection             inspect()
    {
        return /*static*/ this.inspection;
    }


    //
    //  Public (Unit tests)
    //
    public final boolean                test_shape()
    {
        final Shape                     circle = Shape.create(z, "circle");

        circle.skew();

        return true;
    }
}
