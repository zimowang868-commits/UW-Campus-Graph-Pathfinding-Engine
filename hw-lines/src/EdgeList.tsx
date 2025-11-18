/*
 * Copyright (C) 2022 Kevin Zatloukal and James Wilcox.  All rights reserved.  Permission is
 * hereby granted to students registered for University of Washington
 * CSE 331 for use solely during Autumn Quarter 2022 for purposes of
 * the course.  No other use, copying, distribution, or modification
 * is permitted without prior written consent. Copyrights for
 * third-party components of this work must be honored.  Instructors
 * interested in reusing these course materials should contact the
 * author.
 */

import React, {Component} from 'react';

interface EdgeListProps {
    onChange(edges: Edge[]): void;
    // called when a new edge list is ready
    // TODO: once you decide how you want to communicate the edges to the App, you should
    // change the type of edges so it isn't `any`
}

interface EdgeListState{
    text: string
}

export interface Edge {
    x1: number;
    y1: number;
    x2: number;
    y2: number;
    color: string;
}

/**
 * A text field that allows the user to enter the list of edges.
 * Also contains the buttons that the user will use to interact with the app.
 */
class EdgeList extends Component<EdgeListProps, EdgeListState> {

    // The constructor to build the props
    constructor(props: EdgeListProps){
        super(props);
        this.state = {
            text: "Hiiii :)",
        };
    }

    textAreaCase(event: any) {
        this.setState({
            text: event.target.value
        })
    }

    // Create the text area and draw the lines in graph
    // Print error message if coordinates are invalid numbers.
    createEdge(): Edge[] {
        let list: Edge[] = [];
        let str: string = "";
        let totalEdge = this.state.text.split("\n");

        for (let i = 0; i < totalEdge.length; i++) {
            let val = totalEdge[i].split(" ")
            let edge: Edge = {
                x1: parseFloat(val[0]),
                y1: parseFloat(val[1]),
                x2: parseFloat(val[2]),
                y2: parseFloat(val[3]),
                color: val[4],
            }

            if (edge.x1 < 0 || edge.x2 < 0 || edge.y1 < 0 || edge.y2 < 0 || Number.isNaN(edge.x1)
                || Number.isNaN(edge.x2) || Number.isNaN(edge.y1) || Number.isNaN(edge.y2)) {
                str += val.toString().replaceAll(",", " ") + "\n";
            } else {
                list.push(edge);
            }
        }
        if (str.length != 0) {
            alert("Error: Invalid Edges");
        }
        return list;
    }

    render() {
        return (
            <div id="edge-list">
                Edges <br/>
                <textarea
                    rows={5}
                    cols={30}
                    onChange={(event) => this.textAreaCase(event)}
                    value={this.state.text}
                /> <br/>
                <button onClick={() => {this.props.onChange(this.createEdge())}}>Draw</button>
                <button onClick={() => {
                                        let list : Edge[] = [];
                                        this.props.onChange(list)
                                        }}>Clear</button>
            </div>
        );
    }
}

export default EdgeList;
