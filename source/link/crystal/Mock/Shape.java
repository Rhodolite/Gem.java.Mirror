//  Copyright (c) 2018 Joy Diamond.  All rights reserved.


package link.crystal.Mock;


import link.crystal.Gem.Core.Zone;
import link.crystal.Gem.Inspection.Inspection;
import link.crystal.Gem.Interface.Inspectable;
import link.crystal.Mirror.MirrorProxy;


public final class  Shape
    extends         MirrorProxy<Shape, link.crystal.Alpha.Shape>
//  extends         Gem_Object <Inspection>
//  extends         Object
    implements      Inspectable<Inspection>//,
{
    private static final Inspection     inspection = Inspection.create("Mock.Shape");


    //
    //  Constructor & Factory
    //
    private                             Shape(final Zone z, final link.crystal.Alpha.Shape client)
    {
        super(z, client);
    }


    public static final Shape           create(final Zone z, final String shape_name)
    {
        final Class<link.crystal.Alpha.Shape>   meta = link.crystal.Alpha.Shape.class;

        final link.crystal.Alpha.Shape  client = link.crystal.Alpha.Shape.create(z, shape_name);

        return new Shape(z, client);
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
    //  Public
    //
    public final void                   skew()
    {
        final link.crystal.Alpha.Shape  client = this.client;

        line("Mirror.skew");

        client.skew();
    }
}
